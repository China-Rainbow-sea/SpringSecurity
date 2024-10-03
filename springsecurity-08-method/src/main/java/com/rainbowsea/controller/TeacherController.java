package com.rainbowsea.controller;

import com.rainbowsea.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource  // set注入设置
    private TeacherService teacherService;

    @GetMapping("/query")
    public String queryInfo() {
        return teacherService.query();
    }


    @GetMapping("/add")
    public String addInfo() {
        return teacherService.add();
    }


    @GetMapping(value = "/delete")
    public String delete() {
        return teacherService.delete();
    }


    @GetMapping(value = "/update")
    public String update() {
        return teacherService.update();
    }

}
