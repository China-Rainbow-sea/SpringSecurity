package com.rainbowsea.config;

import com.rainbowsea.filter.JwtCheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    private JwtCheckFilter jwtCheckFilter;

    @Resource
    private MyAutheticationSuccessHandler myAutheticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class); // 该过滤器jwtCheckFilter
        // ，在UsernamePasswordAuthenticationFilter 之前，执行
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().successHandler(myAutheticationSuccessHandler).permitAll();
        // 不创建 session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 关闭 session 会话机制，不用
        // session会话机制存储认证

    }
}
