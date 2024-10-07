package com.rainbowsea.config;

import com.rainbowsea.filter.ValidateCodeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * 配置SpringSecurity 的加密的编码设置
 */

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private ValidateCodeFilter validateCodeFilter;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 在用户名密码认证过滤器前添加图片验证码过滤器
        // 注意：不要弄错是：UsernamePasswordAuthenticationFilter.class
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        // 所有请求，都需要认证
        http.authorizeRequests()
                .mvcMatchers("/code/image")
                .permitAll()  // 放开验证码的请求
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/toLogin")  // 配置登录页面
                .usernameParameter("uname")
                .passwordParameter("pwd")
                .loginProcessingUrl("/login/doLogin") // 单击登录后进入 url
                .successForwardUrl("/index/toIndex")
                .permitAll();  // 放开验证码的请求

        http.csrf().disable(); // 关闭跨域请求保护
        // 禁用csrf跨站请求，注意不要写错了
        //http.csrf().disable();
    }
}
