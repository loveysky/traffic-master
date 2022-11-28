package com.fan.system.controller;

import com.fan.api.code.SystemCode;
import com.fan.api.commons.ResponseResult;
import com.fan.api.commons.SystemUtils;
import com.fan.system.info.RoleInfo;
import com.fan.system.service.RoleService;
import com.fan.system.service.impl.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 添加一个角色
     * @param roleInfo 角色
     * @return 添加成功返回true，否则返回false
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResponseResult addRole(RoleInfo roleInfo){
        //参数为空
        if(SystemUtils.isNull(roleInfo)){
            logger.error("system role addRole roleInfo is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ROLE_ERROR_ADD_FAIL_PARAM_NULL, "参数为空");
        }
        if(SystemUtils.isNullOrEmpty(roleInfo.getRname())){
            logger.error("system role addRole name is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ROLE_ERROR_ADD_FAIL_PARAM_NULL, "参数为空");
        }
        if(SystemUtils.isNullOrEmpty(roleInfo.getRtype())){
            logger.error("system role addRole type is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ROLE_ERROR_ADD_FAIL_PARAM_NULL, "参数为空");
        }

        boolean save = roleService.save(roleInfo);
        if(save){
            logger.info("system role add success");
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS, "保存成功");
        }else {
            logger.error("system user add fail");
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR, "保存失败");

        }

    }

    /**
     * 查看所有角色
     * @return 返回所有角色list
     */
    @RequestMapping(value = "/getRole", method = RequestMethod.POST)
    public ResponseResult getRole(){
        List<RoleInfo> list = roleService.list();

        if( !SystemUtils.isNull(list)){
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS, "查询成功", list);
        }else {
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR, "查询失败");
        }
    }
}
