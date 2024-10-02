package com.rainbowsea.springsecurity02hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @GetMapping("/query")
    public String queryInfo() {
        return "I am a teacher, My name is Thomas!";
    }

}
