package com.fan.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fan.system.info.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @date: 2022/11/19 - 11 - 19 - 23:27
 * @version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {

}
