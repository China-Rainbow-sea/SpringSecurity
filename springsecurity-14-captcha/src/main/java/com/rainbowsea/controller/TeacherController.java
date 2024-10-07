package com.rainbowsea.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('teacher:query')")  // 预授权；需要开启全局 ，在springSecurity的配置类上，或者是场景启动器上
    public String queryInfo(){
        return "I am a teacher!";
    }
}
