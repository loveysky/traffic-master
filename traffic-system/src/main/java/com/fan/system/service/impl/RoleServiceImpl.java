package com.fan.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fan.system.info.RoleInfo;
import com.fan.system.mapper.RoleMapper;
import com.fan.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2022/11/28 - 11 - 28 - 1:46
 * @version: 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleInfo> implements RoleService {
    @Autowired
    RoleMapper roleMapper;
}
