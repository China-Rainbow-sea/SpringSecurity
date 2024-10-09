package com.powernode.dao;

import com.powernode.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserDao {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    SysUser getByUserName(@Param("userName") String userName);
}
