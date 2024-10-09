package com.powernode.service.impl;

import com.powernode.entity.SysUser;
import com.powernode.service.SysMenuService;
import com.powernode.service.SysUserService;
import com.powernode.vo.MySecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(username);
        if(null==sysUser){
            throw new UsernameNotFoundException("该用户不存在");
        }
        //根据用户id获取该用户所拥有的权限，List<SimpleGrantedAuthority>
        List<String> userPermissions = sysMenuService.queryPermissionsByUserId(sysUser.getUserId());
        //遍历权限，把权限放到
        List<SimpleGrantedAuthority> authorityList=new ArrayList<>();
        for (String userPermission : userPermissions) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userPermission);
            authorityList.add(simpleGrantedAuthority);
        }
        List<SimpleGrantedAuthority> authorityList1 = userPermissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());


        MySecurityUser securityUser=new MySecurityUser(sysUser);
        securityUser.setAuthorityList(authorityList);
        return securityUser;
    }
}
