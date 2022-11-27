package com.fan.system.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fan.system.info.UserInfo;
import com.fan.system.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @date: 2022/11/28 - 11 - 28 - 0:42
 * @version: 1.0
 * 生成Token
 */
@Component
public class TokenUtils {
    private static UserServiceImpl staticUserService;

    @Resource
    private UserServiceImpl userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    /**
     * 生成token
     * @return
     */
    public static String genToken(String userId, String sign) {

        // 将 user id 保存到 token 里面,作为载荷
        return JWT.create().withAudience(userId)
                // 3小时后token过期
                .withExpiresAt(DateUtil.offsetHour(new Date(), 3))
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(sign));
    }

    /**
     * 获取当前登录的用户信息
     * @return UserInfo对象
     */
    public static UserInfo getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


}
