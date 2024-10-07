package com.rainbowsea.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class LoginController {


    @RequestMapping("/toLogin")
    public String toLogin() {
        // 返回 thymeleaf 的逻辑视图名，物理视图 = 前缀 + 逻辑视图名 + 后缀
        // /templates/+login+.html
        return "login"; // 逻辑视图
    }

}
