package com.rainbowsea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.rainbowsea.mapper")
public class Springsecurity13ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity13ThymeleafApplication.class, args);
    }

}
