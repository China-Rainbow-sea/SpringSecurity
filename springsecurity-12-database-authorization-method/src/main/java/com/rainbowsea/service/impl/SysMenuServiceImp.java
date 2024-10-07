package com.rainbowsea.service.impl;

import com.rainbowsea.entity.SysMenu;
import com.rainbowsea.mapper.SysMenuDao;
import com.rainbowsea.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class SysMenuServiceImp implements SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public List<String> queryPermissionsByUserId(Integer userId) {
        return sysMenuDao.queryPermissionsByUserId(userId);
    }
}
