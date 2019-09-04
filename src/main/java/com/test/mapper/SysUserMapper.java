package com.test.mapper;

import com.test.entity.SysUser;

import java.util.Set;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getUser(SysUser sysUser);

    Set<String> findPermissionsByUserId(Integer id);
}