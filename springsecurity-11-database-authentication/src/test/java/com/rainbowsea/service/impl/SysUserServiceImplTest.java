package com.rainbowsea.service.impl;

import com.rainbowsea.entity.SysUser;
import com.rainbowsea.service.SysUserService;
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
        System.out.println(sysUser);

    }
}