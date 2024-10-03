package com.rainbowsea.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  // 定义配置类
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()  //授权 http 请求
                .anyRequest(); // 任何请求
                //.denyAll(); // 拒绝
        //.permitAll();  // 允许任何请求通过
         http.formLogin().permitAll(); // 允许表单登录 permit :允许
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()  // 授权请求
                // 三个  url 匹配都是一样的效果，建议使用 mvcMatchers
                //.regexMatchers("/student/**")
                //.antMatchers("/student/**")
                //.mvcMatchers("/student/**") // 匹配这个url
                .mvcMatchers("/student/**") // 匹配这个url
                //.hasAuthority()  是否有单个权限
                //.access("hasAuthority('student:query') or hasRole('admin')")
                //.hasRole()  是否有单个角色
                //.hasAnyRole()  是否有任意角色
                .hasAnyAuthority("student:add")  // 拥有这个权限的用户可以访问/student/**
                .mvcMatchers("/teacher/**")  // 匹配 这个url
                .hasAnyAuthority("ROLE_teacher")  // 拥有这个权限的用户可以访问 /teacher/**
                .anyRequest() // 任何请求
                .authenticated(); // 都需要登录（意思就是说：没有配置 mvc的只要登录成功就可以，访问，不需要啥权限）
        http.formLogin().permitAll();  // 允许表单登录 permit; 允许
    }
}


