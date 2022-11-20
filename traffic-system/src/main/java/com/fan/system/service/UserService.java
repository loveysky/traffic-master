package com.fan.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fan.system.info.UserInfo;
import org.springframework.stereotype.Service;

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
}
