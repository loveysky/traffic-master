package com.fan.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fan.api.commons.ResponseResult;
import com.fan.system.info.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2022/11/19 - 11 - 19 - 23:29
 * @version: 1.0
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 插入用户
     * @param userInfo 用户类
     * @return 成功返回true，失败返回false
     */
    boolean addUser(UserInfo userInfo);

    /**
     * 删除用户(一条或多条)
     * @param uid user id
     * @return  false删除失败   true删除成功
     */
    boolean deleteUser(String uid);


    /**
     * 修改用户信息
     * @param userInfo user
     * @return 修改成功true，修改失败false
     */
    boolean updateUserById(UserInfo userInfo);

    /**
     * 查询所有用户信息
     * @return 所有用户
     */
    List<UserInfo> getUserAll();

    /**
     * 根据姓名或者手机号查询
     * @param string string
     * @return 根据条件查询到的所有用户列表
     */
    UserInfo getUserByWhere(String string);

    /**
     * 登录接口
     * @param userInfo 用户实体
     * @return UserInfo
     */
    UserInfo login(UserInfo userInfo);
}
