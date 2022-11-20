package com.fan.system.controller;


import com.fan.api.code.SystemCode;
import com.fan.api.commons.SystemUtils;
import com.fan.api.commons.ResponseResult;
import com.fan.system.info.UserInfo;
import com.fan.system.service.UserService;
import org.apache.tomcat.util.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2022/11/19 - 11 - 19 - 20:01
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //哪个类打印的日志
    final Logger logger = LoggerFactory.getLogger(UserController.class);

    final int NAME_MINI_LENGTH = 3; //用户名最小长度

    @Autowired
    UserService userService;

    /**
     * 保存一个用户
     * @param userInfo 用户
     * @return responseResult
     * result为true 插入成功
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseResult addUser(UserInfo userInfo){
        ResponseResult responseResult;
        //参数为空
        if(SystemUtils.isNull(userInfo)){
            logger.error("system user addUser userInfo is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NULL, "参数为空");
        }
        //用户名为空
        if(SystemUtils.isNullOrEmpty(userInfo.getUaccount())){
            logger.error("system user addUser account is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_NULL, "用户名为空");
        }
        //用户名长度合法 >3
        if(userInfo.getUaccount().trim().length() <= NAME_MINI_LENGTH){
            logger.error("system user addUser account length illegal");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_SIZE, "用户名长度不合法");
        }
        //密码加密
        userInfo.setUpass(DigestUtils.md5DigestAsHex(userInfo.getUpass().getBytes()));

        boolean result = userService.addUser(userInfo);

        if(result){
            return ResponseResult.buildResponseResult();
        }else {
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL);
        }
    }


}
