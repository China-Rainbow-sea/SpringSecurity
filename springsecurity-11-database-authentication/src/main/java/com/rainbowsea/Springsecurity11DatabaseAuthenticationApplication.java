package com.rainbowsea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.rainbowsea.dao")
public class Springsecurity11DatabaseAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity11DatabaseAuthenticationApplication.class, args);
    }

}
