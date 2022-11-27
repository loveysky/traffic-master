package com.fan.system.controller;

import com.fan.system.service.RoleService;
import com.fan.system.service.impl.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2022/11/28 - 11 - 28 - 1:42
 * @version: 1.0
 * 角色管理模块
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    //哪个类打印的日志
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    RoleServiceImpl roleService;
}
