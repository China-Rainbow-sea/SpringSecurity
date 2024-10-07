package com.powernode.service.impl;

import com.powernode.entity.SysUser;
import com.powernode.service.SysUserService;
import com.powernode.vo.MySecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(username);
        if(null==sysUser){
            throw new UsernameNotFoundException("该用户不存在");
        }

        MySecurityUser securityUser=new MySecurityUser(sysUser);

        return securityUser;
    }
}
