package com.rainbowsea.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration  // 自定义配置类
public class MySecurityUserConfig {

    @Bean  // 没有指明名称，默认方法名就是：id/名称
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("eric")
                .password(passwordEncoder().encode("123"))  // 用户密码必须加密，否则报错
                .roles("student")  // 角色的前面加上 ROLE_student 的角色权限
                .build();


        UserDetails user2 = User.builder()
                .username("thomas")
                .password(passwordEncoder().encode("123")) // 用户密码必须加密，否则报错
                .authorities("teacher:query")  // 配置权限
                .build();

        UserDetails user3 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123")) // 用户密码必须加密
                .authorities("teacher:query", "teacher:add", "teacher:update", "teacher:delete")
                .build();

        // 注意：当 roles 和 authorities 重复出现的时候，以最后一个配置的为准

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 添加用户
        manager.createUser(user);
        manager.createUser(user2);
        manager.createUser(user3);
        return manager;

    }


    /*
    自定义用户必须配置密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder 根本本质上就没有加密

        return new BCryptPasswordEncoder();
    }
}



