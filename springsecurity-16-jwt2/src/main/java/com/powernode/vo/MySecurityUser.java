package com.powernode.vo;

import com.powernode.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySecurityUser implements UserDetails {
    private final SysUser sysUser;
    //用于存储权限的list
    private List<SimpleGrantedAuthority> authorityList;
    public MySecurityUser(SysUser sysUser){
        this.sysUser=sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    /**
     * 返回用户所拥有的权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    public void setAuthorityList(List<SimpleGrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    @Override
    public String getPassword() {
        String myPassword=sysUser.getPassword();
        sysUser.setPassword(null); //擦除我们的密码，防止传到前端
        return myPassword;
    }

    @Override
    public String getUsername() {
        return this.sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return sysUser.getAccountNoExpired().equals(1);
    }

    @Override
    public boolean isAccountNonLocked() {
        return sysUser.getAccountNoLocked().equals(1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return sysUser.getCredentialsNoExpired().equals(1);
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getEnabled().equals(1);
    }
}
