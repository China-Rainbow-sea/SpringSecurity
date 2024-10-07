package com.rainbowsea.service.impl;

import com.rainbowsea.entity.SysUser;
import com.rainbowsea.service.SysUserService;
import com.rainbowsea.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(username);

        if(null == sysUser) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        SecurityUser securityUser = new SecurityUser(sysUser);

        return securityUser;


    }
}
