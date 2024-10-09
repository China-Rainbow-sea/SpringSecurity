package com.rainbowsea.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbowsea.entity.SysUser;
import com.rainbowsea.util.JwtUtils;
import com.rainbowsea.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // 如果是登录请求url，直接放行
        if (requestURI.equals("/login") || requestURI.equals("/code/image")) {
            doFilter(request,response,filterChain);
            return;
        }

        String strAuth = request.getHeader("Authorization");
        if(StringUtils.isEmpty(strAuth)) {
             HttpResult httpResult = HttpResult.builder()
                    .code(0)
                    .msg("Authorization 为空")
                    .build();

             printToken(request,response,httpResult);
             return;

        }

        // 将 bearer 替换为 "" ，就是删除 bearer
        String jwtToken = strAuth.replace("bearer", "");
        if(StringUtils.containsWhitespace(jwtToken)) {
            HttpResult httpResult = HttpResult.builder()
                    .code(0)
                    .msg("jwt 为空")
                    .build();

            printToken(request,response,httpResult);
            return;

        }

        // 校验 jwt
        boolean verifyToken = jwtUtils.verifyToken(jwtToken);
        if(!verifyToken) {
            HttpResult httpResult = HttpResult.builder()
                    .code(0)
                    .msg("jwt 非法")
                    .build();

            printToken(request,response,httpResult);
            return;
        }

        // 从 jwt 里获取用户信息和权限信息
        String userInfo = jwtUtils.getUserInfoFromToken(jwtToken);
        List<String> userAuthList = jwtUtils.getUserAuthFromToken(jwtToken);
        // 反序列化成SysUser对象
        SysUser sysUser = objectMapper.readValue(userInfo, SysUser.class);
        List<SimpleGrantedAuthority> authorityList = userAuthList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //用户名密码认证 token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(sysUser,null,authorityList);
        // 把 token放到安全上下文：securityContext
        SecurityContextHolder.getContext().setAuthentication(token);
        doFilter(request,response,filterChain);  // 放行
    }


    private void printToken(HttpServletRequest request, HttpServletResponse response, HttpResult httpResult) throws IOException {
        String strResponse = objectMapper.writeValueAsString(httpResult);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(strResponse);
        writer.flush();
    }
}
