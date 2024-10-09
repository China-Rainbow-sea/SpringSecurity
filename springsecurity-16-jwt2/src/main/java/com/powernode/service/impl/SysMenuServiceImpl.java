package com.powernode.service.impl;

import com.powernode.dao.SysMenuDao;
import com.powernode.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuDao sysMenuDao;
    @Override
    public List<String> queryPermissionsByUserId(Integer userId) {
        return sysMenuDao.queryPermissionsByUserId(userId);
    }
}
