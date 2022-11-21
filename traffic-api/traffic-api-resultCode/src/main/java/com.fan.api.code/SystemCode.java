package com.fan.api.code;

/**
 * @date: 2022/11/19 - 11 - 19 - 12:03
 * @version: 1.0
 * 系统管理模块错误码
 * 10000 - 12999
 */
public interface SystemCode {

    /**
     * 错误  提示  警告
     */
    //系统通用
    String TRAFFIC_SYSTEM_SUCCESS = "000000";//系统通用成功状态码
    String TRAFFIC_SYSTEM_ERROR = "999999";//系统通用失败状态码

    //用户管理		10000 - 10999
    //添加
    String SYSTEM_USER_ERROR_ADD_FAIL = "10000"; //用户添加失败
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NULL = "10001"; //请求参数为空
    String SYSTEM_USER_ERROR_ADD_FAIL_NAME_NULL = "10002";  //用户名为空
    String SYSTEM_USER_ERROR_ADD_FAIL_NAME_SIZE = "10003";  //用户名长度不对


    //删除
    String SYSTEM_USER_ERROR_DELETE_FAIL_UID_NULL = "10020";  //uid为空


    //修改
    String SYSTEM_USER_ERROR_UPDATE_FAIL_UID_NULL = "10020";  //uid为空







    //角色管理		11000 - 11999

    String SYSTEM_ROLE_ERROR_ADD_FAIL = "11000"; //角色添加失败

    //权限管理		12000 - 12999






}
