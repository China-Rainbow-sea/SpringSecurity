package com.rainbowsea.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("query")
    public String queryInfo() {
        return "I am a administrator, My name is Obama!";
    }
}
