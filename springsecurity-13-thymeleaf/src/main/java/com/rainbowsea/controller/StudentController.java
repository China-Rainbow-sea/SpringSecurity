package com.rainbowsea.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/query")
    @PreAuthorize("hasAnyAuthority('student:query')")  // 预授权；需要开启全局 ，在springSecurity的配置类上，或者是场景启动器上
    public String queryInfo() {
        // /templates/student/query.html
        return "student/query";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('student:add')") // 预授权；需要开启全局 ，在springSecurity的配置类上，或者是场景启动器上
    public String addInfo() {
        return "student/add";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('student:update')") // 预授权；需要开启全局 ，在springSecurity的配置类上，或者是场景启动器上
    public String updateInfo() {
        return "student/update";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('student:delete')") // 预授权；需要开启全局 ，在springSecurity的配置类上，或者是场景启动器上
    public String deleteInfo() {
        return "student/delete";
    }

    @GetMapping("/export")
    @PreAuthorize("hasAnyAuthority('student:export')") // 预授权；需要开启全局 ，在springSecurity的配置类上，或者是场景启动器上
    public String exportInfo() {
        return "student/export";
    }
}
