package com.powernode.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/query")
    public String queryInfo(){
        return "query student";
    }
    @GetMapping("/add")
    public String addInfo(){
        return "add  student!";
    }
    @GetMapping("/update")
    public String updateInfo(){
        return "update student";
    }
    @GetMapping("/delete")
    public String deleteInfo(){
        return "delete  student!";
    }
    @GetMapping("/export")
    public String exportInfo(){
        return "export  student!";
    }
}