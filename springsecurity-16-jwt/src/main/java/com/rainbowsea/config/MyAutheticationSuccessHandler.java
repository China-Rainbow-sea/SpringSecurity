package com.rainbowsea.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbowsea.entity.SysUser;
import com.rainbowsea.util.JwtUtils;
import com.rainbowsea.vo.HttpResult;
import com.rainbowsea.vo.SecurityUser;
import com.sun.xml.internal.fastinfoset.tools.PrintTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Slf4j
public class MyAutheticationSuccessHandler implements AuthenticationSuccessHandler {


    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        SysUser sysUser = securityUser.getSysUser();
        String strUserInfo = objectMapper.writeValueAsString(sysUser);
        // 获取用的权限信息
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) securityUser.getAuthorities();

        // collect 收集
        List<String> authList =
                authorities.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList());
        // 生成 jwt
        String jwtToken = jwtUtils.createJwt(strUserInfo, authList);
        HttpResult httpResult = HttpResult.builder()
                .code(1)
                .msg("jwt生成成功")
                .data(jwtToken)
                .build();
        printToken(request,response,httpResult);
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
