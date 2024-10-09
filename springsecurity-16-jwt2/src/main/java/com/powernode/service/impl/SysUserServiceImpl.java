package com.powernode.service.impl;

import com.powernode.dao.SysUserDao;
import com.powernode.entity.SysUser;
import com.powernode.service.SysUserService;
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
