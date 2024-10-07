package com.rainbowsea.service.impl;

import com.rainbowsea.dao.SysUserDao;
import com.rainbowsea.entity.SysUser;
import com.rainbowsea.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {


    @Resource
    private SysUserDao sysUserDao;


    @Override
    public SysUser getByUserName(String userName) {
        return sysUserDao.getByUserName(userName);
    }
}
