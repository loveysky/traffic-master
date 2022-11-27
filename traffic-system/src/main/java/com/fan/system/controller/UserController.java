package com.fan.system.controller;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @date: 2022/11/19 - 11 - 19 - 20:01
 * @version: 1.0
 * 用户管理模块
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //哪个类打印的日志
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final int NAME_MINI_LENGTH = 3; //用户名最小长度

    @Autowired
    UserService userService;

    /**
     * 保存一个用户
     * @param userInfo 用户
     * @return responseResult
     * result为true 插入成功
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseResult addUser(UserInfo userInfo) {
        logger.info("addUser start");
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

        //初始化用户状态为正常
        userInfo.setUtype(0);

        boolean result = userService.addUser(userInfo);

        if(result){
            logger.info("system user add success");
            return ResponseResult.buildResponseResult();
        }else {
            logger.error("system user add error");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL);
        }
    }


    /**
     * 删除用户请求
     * @return 是否成功
     * @param uid uid = "1" 删除一个;   uid = "1,2,3" 删除多个
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseResult deleteUser(String uid){
        ResponseResult responseResult;
        //传的参数为空
        if(SystemUtils.isNullOrEmpty(uid)){
            logger.error("system user delete uid is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_DELETE_FAIL_UID_NULL, "id为空");
        }
        boolean result = userService.deleteUser(uid);
        if(result){
            logger.info("system user delete success");
            responseResult = ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,"删除成功");
        }else {
            logger.error("system user delete error");
            responseResult = ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR, "删除失败");
        }
        return responseResult;
    }

    /**
     * 修改用户信息
     * @param userInfo user实体信息
     * @return 修改成功或失败
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseResult updateUser(UserInfo userInfo){
        ResponseResult responseResult;
        String uid = userInfo.getUid()+"";
        //没有uid，或者uid为0
        if(SystemUtils.isNullOrEmpty(uid) || Integer.parseInt(uid) == 0){
            logger.error("system user update uid is null or uid = 0");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_UPDATE_FAIL_UID_NULL, "id为空");
        }
        boolean result = userService.updateUserById(userInfo);
        if(result){
            logger.info("system user update success");
            responseResult = ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,"修改成功");
        }else {
            logger.error("system user update error");
            responseResult = ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR, "修改失败");
        }
        return responseResult;
    }

    /**
     * 查询所有用户
     * @return 查询到的所有用户列表
     */
    @RequestMapping(value = "/getUserAll")
    public ResponseResult getUserAll(){
        List<UserInfo> userAll = userService.getUserAll();
        if(SystemUtils.isNull(userAll)){
            logger.error("system user 查询结果为空");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_GET_FILE_RESULT_NULL,"查询结果为空");
        }
        return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,"查询成功",userAll);

    }

    /**
     * 根据姓名或者手机号查询
     * @return 根据条件查询到的所有用户列表
     */
    @RequestMapping(value = "/getUserByWhere")
    public ResponseResult getUserByWhere(String string){
        logger.info("system user getUserByWhere start");
        //判断不为空
        if(SystemUtils.isNullOrEmpty(string)){
            logger.error("system user getUserByWhere param is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_GET_FAIL_PARAM_NULL,"请求参数为空");
        }
        //查询
        logger.info("system user getUserByWhere userService start");
        UserInfo user =  userService.getUserByWhere(string);
        if(SystemUtils.isNull(user)){
            logger.error("system user getUserByWhere user is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_GET_FILE_RESULT_NULL,"查询结果为空");

        }
        logger.info("system user getUserByWhere end and success");
        return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,"查询成功",user);
    }

    /**
     * 登录接口
     * @param userInfo 用户实体
     * @return
     * RequestBody注解可以把前端传过来的json转化成java对象
     */
    @RequestMapping(value = "/userLogin" ,method = RequestMethod.POST)
    public ResponseResult userLogin(@RequestBody UserInfo userInfo){
        if(userInfo == null){
            logger.error("system user userLogin get param is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ERROR_AUTHENTICATION_PARAM_NULL, "请求参数为空");
        }

        //利用hutool工具类 检验是否为空
        if(StrUtil.isBlank(userInfo.getUaccount())){
            logger.error("user login account is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ERROR_AUTHENTICATION_USERNAME_NULL, "用户名或密码为空");
        }
        if(StrUtil.isBlank(userInfo.getUpass())){
            logger.error("user login password is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ERROR_AUTHENTICATION_USERPASS_NULL, "用户名或密码为空");
        }
        UserInfo u = userService.login(userInfo);

        if(SystemUtils.isNull(u)){
            logger.error("system user userLogin select user is null");
            return ResponseResult.buildResponseResult(SystemCode.SYSTEM_ERROR_AUTHENTICATION_USERPASS_ERROR,"用户名或密码错误");
        }
        logger.info("system user userLogin select user end and success");
        return ResponseResult.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS, "用户登录成功", u);
    }
}
