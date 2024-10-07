package com.rainbowsea.vo;

import com.rainbowsea.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {

    private final SysUser sysUser;

    public SecurityUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        String userPassword = this.sysUser.getPassword();
        //注意清除密码
        this.sysUser.setPassword(null);
        return userPassword;

    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
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

   /* private final SysUser sysUser;

    public SecurityUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // 没有配置权限
        return null;
    }

    @Override
    public String getPassword() {
        return this.sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.sysUser.getAccountNoExpired().equals(1);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.sysUser.getAccountNoLocked().equals(1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.sysUser.getCredentialsNoExpired().equals(1);
    }

    @Override
    public boolean isEnabled() {
        return this.sysUser.getEnabled().equals(1);
    }*/
}
