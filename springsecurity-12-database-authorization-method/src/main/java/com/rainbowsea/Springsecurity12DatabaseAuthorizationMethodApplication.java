package com.rainbowsea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rainbowsea.mapper")
public class Springsecurity12DatabaseAuthorizationMethodApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity12DatabaseAuthorizationMethodApplication.class, args);
    }

}
