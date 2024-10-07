package com.rainbowsea.service;

import java.util.List;

public interface SysMenuService {

    // 注意：接口不需要 @Param("userId")
    List<String> queryPermissionsByUserId(Integer userId);

}
