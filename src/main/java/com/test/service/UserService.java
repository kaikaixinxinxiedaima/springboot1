package com.test.service;

import com.test.entity.SysUser;
import com.test.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser getUser(SysUser sysUser){
        return sysUserMapper.getUser(sysUser);
    }

    public Set<String> findPermissionsByUserId(Integer id) {
        return sysUserMapper.findPermissionsByUserId(id);
    }
}
