package com.rainbowsea.service.impl;

import com.rainbowsea.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Override
    @PreAuthorize("hasAnyAuthority('teacher:add')")  // 方法当中配置，权限，注意：需要开启全局安全才行
    public String add() {
        log.info("添加教师成功");
        return "添加教师成功";
    }

    @Override
    @PreAuthorize("hasAnyAuthority('teacher:update')")  // 方法当中配置，权限，注意：需要开启全局安全才行
    public String update() {
        log.info("修改教师成功");
        return "修改教师成功";
    }

    @Override
    @PreAuthorize("hasAnyAuthority('teacher:delete')")  // 方法当中配置，权限，注意：需要开启全局安全才行
    public String delete() {
        log.info("删除教师成功");
        return "删除教师成功";
    }

    @Override
    @PreAuthorize("hasAnyAuthority('teacher:query')")
    public String query() {
        log.info("查询教师成功");
        return "查询教师成功";
    }
}
