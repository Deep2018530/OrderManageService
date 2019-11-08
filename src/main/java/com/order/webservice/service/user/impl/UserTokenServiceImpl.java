package com.order.webservice.service.user.impl;

import com.order.webservice.config.RedisConfiguration;
import com.order.webservice.service.user.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
    public String setToken(BigInteger userId) {
        Objects.requireNonNull(userId);
        String token = getToken();
        redisTemplate.opsForValue().set(token, userId, TOKEN_ACTIVE, TimeUnit.HOURS);
        return token;
    }

    /**
     * 验证token是否过期
     *
     * @param token
     * @return
     */
    @Override
    public Boolean checkToken(String token) {
        Object userId = redisTemplate.opsForValue().get(token);
        if (Objects.isNull(userId)) {
            return false;
        }
        redisTemplate.opsForValue().set(token, userId, TOKEN_ACTIVE, TimeUnit.HOURS);
        return true;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
