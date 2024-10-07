package com.rainbowsea.service.impl;

import com.rainbowsea.entity.SysUser;
import com.rainbowsea.service.SysMenuService;
import com.rainbowsea.service.SysUserService;
import com.rainbowsea.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(username);

        if (null == sysUser) {
            throw new UsernameNotFoundException("该用户不存在");
        }


        // 根据用户id 获取该用户拥有的权限
        List<String> userPermissions = sysMenuService.queryPermissionsByUserId(sysUser.getUserId());

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();


        // 方法一
  /*      for (String userPermission : userPermissions) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userPermission);
            authorityList.add(simpleGrantedAuthority);
        }*/

        // 方法二： 使用 stream 流进行遍历赋值,使用stream流来转换
        List<SimpleGrantedAuthority> authorityList2 =
                userPermissions.stream().map(SimpleGrantedAuthority::new).collect(toList());
        //List<SimpleGrantedAuthority> grantedAuthorities = userPermissions.stream().map(SimpleGrantedAuthority::new).collect(toList());


        SecurityUser securityUser = new SecurityUser(sysUser);
        //securityUser.setAuthorityList(authorityList);
        securityUser.setAuthorityList(authorityList2);


        return securityUser;


    }
}
