package com.powernode.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuService {
    List<String> queryPermissionsByUserId(Integer userId);
}
