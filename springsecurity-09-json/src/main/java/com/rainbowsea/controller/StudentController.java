package com.rainbowsea.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/query")
    public String queryInfo() {
        return "I am a student, My name is Eric!";
    }

}
