package com.rainbowsea.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuService {

    // 注意：接口不需要 @Param("userId")
    List<String> queryPermissionsByUserId(Integer userId);

}
