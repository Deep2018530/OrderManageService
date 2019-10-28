package com.order.webservice.service.user.impl;

import com.order.webservice.config.RedisConfiguration;
import com.order.webservice.service.user.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * created by zhangdingping at 2019/10/28
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConfiguration redisConfiguration;


    //token 有效期
    public static final int TOKEN_ACTIVE = 2;

    /**
     * 设置登录token
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public String setToken(Long userId) {
        Objects.requireNonNull(userId);

        StringBuilder sb = new StringBuilder(30);
        sb.append(redisConfiguration.loginTokenPrefix);
        sb.append(userId);
        String token = getToken();
        redisTemplate.opsForValue().set(sb.toString(), token, TOKEN_ACTIVE, TimeUnit.HOURS);
        return token;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
