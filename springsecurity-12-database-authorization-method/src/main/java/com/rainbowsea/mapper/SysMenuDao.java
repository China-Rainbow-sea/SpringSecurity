package com.rainbowsea.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuDao {

    List<String> queryPermissionsByUserId(@Param("userId") Integer userId);
}
