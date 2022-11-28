package com.fan.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fan.system.info.RoleInfo;
import com.fan.system.info.UserRoleInfo;
import com.fan.system.mapper.RoleMapper;
import com.fan.system.mapper.UserRoleMapper;
import com.fan.system.service.RoleService;
import com.fan.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2022/11/28 - 11 - 28 - 1:46
 * @version: 1.0
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleInfo> implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 通过uid，给某用户添加一个角色
     * @param uid uid
     * @param rid rid
     * @return 成功返回true，失败返回false
     */
    public boolean addByUid(String uid, String rid){

        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUid(Long.valueOf(uid));
        userRoleInfo.setRid(Long.valueOf(rid));

        return this.save(userRoleInfo);
    }

    /**
     *根据用户id，查询用户所拥有的角色，传入一个uid，返回一个list集合
     * @param uid uid
     * @return list
     */
    public List<UserRoleInfo> getByUid(String uid){
        QueryWrapper<UserRoleInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.list(queryWrapper);
    }

}
