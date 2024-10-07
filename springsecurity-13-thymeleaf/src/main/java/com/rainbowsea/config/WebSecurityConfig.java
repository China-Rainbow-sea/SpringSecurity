package com.rainbowsea.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 配置SpringSecurity 的加密的编码设置
 */

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin()
                .loginPage("/toLogin")  // 配置登录页面（自定义 spring security 的拦截登录页面为我们自己的编写的页面）
                .usernameParameter("uname") // 用户名参数
                .passwordParameter("pwd") // 密码参数，前端表单编写提交的
                .loginProcessingUrl("/login/doLogin") // 单击登录后进入 url ，为我们自己编写页面的，登录表单进入的路径
                .failureForwardUrl("/toLogin") // 登录失败，重新跳回到登录页面
                .successForwardUrl("/index/toIndex")  // 登录成功，跳转到自己编写的页面,注意：是controller 控制器的映射路径
                .permitAll(); // 配置登录

        // 注意：上述的路径都是controller 控制器的映射路径


        http.logout().logoutSuccessUrl("/toLogin");  // 配置退出成功的页面，注意：是controller 控制器的映射路径

        http.csrf().disable(); // 关闭跨域请求保护
    }
}
