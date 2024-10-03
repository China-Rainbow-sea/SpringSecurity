package com.rainbowsea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 自定义实现用户详情服务接口
 */
@Configuration // 定义配置类
public class MySecurityUserConfig {
  /*  @Bean
    public UserDetailsService userDetailService() {
//        使用org.springframework.security.core.userdetails.User类来定义用户
        //定义两个用户
        UserDetails user1 = User.builder().username("eric").password("123456").roles("student").build();
        UserDetails user2 = User.builder().username("thomas").password("123456").roles("teacher").build();
        //创建两个用户
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);
        return userDetailsManager;
    }*/

    @Bean
    public UserDetailsService userDetailService( ) {
        UserDetails user1 = User.builder()
                .username("eric")
                .password("123")
                .roles("student")
                .build();
        UserDetails user2 = User.builder()
                .username("thomas")
                .password("123")
                .roles("teacher")
                .build();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }


    /*
    设置加密编码算法
    自定义用户必须：配置密码编码器，否则，无法登录。
    NoOpPasswordEncoder  没有加密，已经被弃用了。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
