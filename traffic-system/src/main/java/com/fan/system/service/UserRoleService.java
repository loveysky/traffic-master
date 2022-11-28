package com.fan.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fan.system.info.RoleInfo;
import com.fan.system.info.UserRoleInfo;

import java.util.List;

/**
 * @date: 2022/11/28 - 11 - 28 - 1:45
 * @version: 1.0
 */
public interface UserRoleService extends IService<UserRoleInfo> {

    /**
     * 通过uid，给某用户添加一个角色
     * @param uid uid
     * @param rid rid
     * @return 成功返回true，失败返回false
     */
    boolean addByUid(String uid, String rid);

    /**
     *根据用户id，查询用户所拥有的角色，传入一个uid，返回一个list集合
     * @param uid uid
     * @return list
     */
    List<UserRoleInfo> getByUid(String uid);
}
