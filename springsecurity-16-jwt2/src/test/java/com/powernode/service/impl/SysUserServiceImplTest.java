package com.powernode.service.impl;

import com.powernode.entity.SysUser;
import com.powernode.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserServiceImplTest {

    @Resource
    private SysUserService sysUserService;
    @Test
    void getByUserName() {
        SysUser sysUser = sysUserService.getByUserName("dddd");
        assertNull(sysUser);
    }
}