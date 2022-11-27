package com.fan.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fan.api.code.SystemCode;
import com.fan.api.commons.ResponseResult;
import com.fan.api.commons.SystemUtils;
import com.fan.system.mapper.UserMapper;
import com.fan.system.info.UserInfo;
import com.fan.system.service.UserService;
import com.fan.system.utils.TokenUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @date: 2022/11/19 - 11 - 19 - 23:30
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService  {
    @Autowired
    UserMapper userMapper;

    /**
     * 插入用户
     * @param userInfo 用户类
     * @return
     */
    @Override
    public boolean addUser(UserInfo userInfo) {
        return this.save(userInfo);
    }

    /**
     * 删除用户
     * 修改用户状态为1
     * @param uid user id
     * @return 成功true 失败false
     */
    @Override
    public boolean deleteUser(String uid) {
        String[] ids = uid.split(",");
        //判断长度
        if(SystemUtils.isNull(ids) || ids.length == 0){
            return false;
        }
        //删除一条
        if(ids.length == 1){
            // 根据 ID 选择修改,
            UserInfo userInfo = this.getById(uid);  //查询
            //判断用户是否已删除
            if(userInfo.getUtype() == 1){
                return true;
            }
            userInfo.setUtype(1);   //修改
            return this.updateById(userInfo);   //更新
        }else { //删除多条

            List<String> list = Arrays.asList(ids); //数组转list
            //根据id的list查询多条
            List<UserInfo> userList = this.listByIds(list);
            //批量修改
            for(int i = 0; i < userList.size(); i++){
                userList.get(i).setUtype(1);
            }
            //更新
            return this.updateBatchById(userList);
        }
    }

    /**
     * 修改用户信息
     * @param userInfo user
     * @return 修改成功true，修改失败false
     */
    @Override
    public boolean updateUserById(UserInfo userInfo) {
        //先查询
        UserInfo user = this.getById(userInfo.getUid());
        //判断姓名
        if(!SystemUtils.isNullOrEmpty(userInfo.getUname())){
            user.setUname(userInfo.getUname());
        }
        //判断账号
        if(!SystemUtils.isNullOrEmpty(userInfo.getUaccount())){
            user.setUaccount(userInfo.getUaccount());
        }
        //判断密码
        if(!SystemUtils.isNullOrEmpty(userInfo.getUpass())){
            //密码加密
            userInfo.setUpass(DigestUtils.md5DigestAsHex(userInfo.getUpass().getBytes()));
        }
        //判断邮箱
        if(!SystemUtils.isNullOrEmpty(userInfo.getUmail())){
            user.setUmail(userInfo.getUmail());
        }
        //判断电话
        if(!SystemUtils.isNullOrEmpty(userInfo.getUphone())){
            user.setUphone(userInfo.getUphone());
        }
        //修改
        boolean result = this.updateById(user);
        return result;
    }

    /**
     * 查询所有用户信息
     * @return 所有用户
     */
    @Override
    public List<UserInfo> getUserAll(){
        return this.list();
    }

    /**
     * 根据姓名或者手机号查询
     * @param userInfo user
     * @return 根据条件查询到的所有用户列表
     */
    @Override
    public UserInfo getUserByWhere(String string){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
        //查询条件判断
        if(string.length() == 11){  //手机号
            queryWrapper.eq("uphone", string);
        }else {
            //姓名
            queryWrapper.eq("uname", string);
        }
        UserInfo userInfo = this.getOne(queryWrapper);
        return userInfo;
    }

    /**
     * 登录
     * @param userInfo 用户实体
     * @return UserInfo
     */

    @Override
    public UserInfo login(UserInfo userInfo){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uaccount",userInfo.getUaccount());
        //加密
        String pass = DigestUtils.md5DigestAsHex(userInfo.getUpass().getBytes());
        queryWrapper.eq("upass",pass);

        //查询用户
        UserInfo one = getOne(queryWrapper);


        //如果是正常用户
        if(one != null && one.getUtype() == 0){
            //设置token
            String token = TokenUtils.genToken(one.getUid()+"", one.getUpass());
            one.setToken(token);
            //设置身份
            //查询user-role表，获取当前user的所有role，查询role表，将拥有的所有角色信息封装返回
            return one;
        }
        return null;
    }

}
