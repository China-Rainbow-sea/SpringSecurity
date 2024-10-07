package com.rainbowsea.mapper;

import com.rainbowsea.entity.SysUser;
import org.apache.ibatis.annotations.Param;


public interface SysUserDao {

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    SysUser getByUserName(@Param("username") String userName);
    // Result type not match for select id="getByUserName" srcType: com. powernode. entity. SysUser targetType: com. rainbowsea. entity. SysUser
}
