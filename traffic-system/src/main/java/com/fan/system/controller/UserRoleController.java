package com.fan.system.controller;

import com.fan.api.code.SystemCode;
import com.fan.api.commons.ResponseResult;
import com.fan.api.commons.SystemUtils;
import com.fan.system.info.UserRoleInfo;
import com.fan.system.service.impl.UserRoleServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @date: 2022/11/28 - 11 - 28 - 23:08
 * @version: 1.0
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    //哪个类打印的日志
    private final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    UserRoleServiceImpl userRoleService;

    /**
     * 通过uid，给某用户添加一个角色
     * @param uid uid
     * @param rid rid
     * @return 成功返回true，失败返回false
     */
    @RequestMapping(value = "/addByUid", method = RequestMethod.POST)
    public ResponseResult addByUid(String uid, String rid){
        logger.info("system user-role add one addByUid Controller start");

        //判断请求参数
        if(SystemUtils.isNullOrEmpty(uid) || SystemUtils.isNullOrEmpty(rid)){
            logger.error("system user-role add uid or rid is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ROLE_ERROR_ADD_FAIL_PARAM_NULL, "请求参数为空");

        }
        //根据uid添加
        logger.info("system user-role add one addByUid service start");
        boolean flag = userRoleService.addByUid(uid, rid);
        logger.info("system user-role add one addByUid service end and success");

        if(flag){
            logger.info("system user-role add end and result  success");
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS, "添加成功");
        }else {
            logger.error("system user-role end and result add fail");
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR, "添加失败");
        }
    }

    /**
     * 根据用户id，查询用户所拥有的角色，传入一个uid，返回一个list集合
     * @param uid uid
     * @return list
     */
    @RequestMapping(value = "/getByUid", method = RequestMethod.POST)
    public ResponseResult getByUid(String uid){
        //判断请求参数
        if(SystemUtils.isNullOrEmpty(uid)){
            logger.error("system user-role getByUid uid is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ROLE_ERROR_ADD_FAIL_PARAM_NULL, "请求参数为空");

        }
        //根据uid查询
        List<UserRoleInfo> list = userRoleService.getByUid(uid);
        if(SystemUtils.isNull(list) || list.size() == 0){
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR, "查询失败");
        }else {
            return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS, "查询成功", list);

        }
    }


}
