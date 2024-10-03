package com.rainbowsea.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  // 定义配置类
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 开启全局方法安全；可以在配置类上，也可以在场景启动器上配置该注解都可以有效果
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // controller 的权限配置，可以在这里配置，通过url 进行配置
        http.authorizeRequests().anyRequest().authenticated(); // 任何请求均需要认证
        http.formLogin().permitAll();
    }
}


