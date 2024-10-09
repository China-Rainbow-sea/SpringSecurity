package com.powernode.dao;

import com.powernode.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserDaoTest {

    @Resource
    private SysUserDao sysUserDao;
    @Test
    void getByUserName() {
        SysUser sysUser = sysUserDao.getByUserName("obama");
        assertNotNull(sysUser);
    }
}