package com.powernode.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysMenuDaoTest {
    @Resource
    private SysMenuDao sysMenuDao;
    @Test
    void queryPermissionsByUserId() {
        List<String> resultList = sysMenuDao.queryPermissionsByUserId(1);
        assertTrue(!resultList.isEmpty()); //判断list不为空
    }
}