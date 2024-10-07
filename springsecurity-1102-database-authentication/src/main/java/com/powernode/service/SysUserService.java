package com.powernode.service;

import com.powernode.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserService {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    SysUser getByUserName(String userName);
}
