package com.fan.system.config.interceptor;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fan.system.exception.ServiceException;
import com.fan.system.info.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fan.api.code.SystemCode;

import com.fan.system.service.impl.UserServiceImpl;

/**
 * 拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServiceException {
        String token = request.getHeader("token");


        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(SystemCode.SYSTEM_ERROR_AUTHENTICATION_NO_TOKEN, "无token，请重新登录");
        }
        // 获取 token 中的 user id
        String uid;
        try {
            uid = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(SystemCode.SYSTEM_ERROR_AUTHENTICATION_FAIL_TOKEN, "token验证失败，请重新登录");
        }
        // 根据token中的uid查询数据库
        UserInfo userInfo = userService.getById(uid);
        if (userInfo == null) {
            throw new ServiceException(SystemCode.SYSTEM_ERROR_AUTHENTICATION_USER_NOTFOUND, "用户不存在，请重新登录");
        }
        // 用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfo.getUpass())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(SystemCode.SYSTEM_ERROR_AUTHENTICATION_FAIL_TOKEN, "token验证失败，请重新登录");
        }
        return true;
    }
}
