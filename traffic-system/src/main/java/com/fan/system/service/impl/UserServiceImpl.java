package com.fan.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fan.system.mapper.UserMapper;
import com.fan.system.info.UserInfo;
import com.fan.system.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2022/11/19 - 11 - 19 - 23:30
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService  {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addUser(UserInfo userInfo) {
        return this.save(userInfo);
    }
}
