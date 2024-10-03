package com.rainbowsea.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@Configuration  // 定义配置类
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AppAutheticationSuccessHandler appAutheticationSuccessHandler;

    @Resource
    private AppAuthenticatiorFailHandler appAuthenticatiorFailHandler;

    @Resource
    private AppLogoutSuccessHandler appLogoutSuccessHandler;

    @Resource
    private AppAccessDenyHandler appAccessDenyHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/student/**")
                .hasRole("student")
                .anyRequest().authenticated();



        // 设置登录成功的处理器
        http.formLogin()
                .successHandler(appAutheticationSuccessHandler)  // 配置认证成功处理器
                .failureHandler(appAuthenticatiorFailHandler) // 配置认知失败处理器
                .permitAll();

        http.logout().logoutSuccessHandler(appLogoutSuccessHandler); // 配置退出成功处理器
        http.exceptionHandling().accessDeniedHandler(appAccessDenyHandler); // 配置访问拒绝处理器

    }
}


