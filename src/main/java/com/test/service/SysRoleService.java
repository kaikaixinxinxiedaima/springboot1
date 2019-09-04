package com.test.service;

import com.alibaba.fastjson.JSONObject;
import com.test.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public Set<String> findRoleNameByUserId(Integer id){
        return sysRoleMapper.findRoleNameByUserId(id);
    }


}
