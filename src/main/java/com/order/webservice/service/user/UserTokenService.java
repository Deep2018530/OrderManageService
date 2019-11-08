package com.order.webservice.service.user;

import java.math.BigInteger;

public interface UserTokenService {

    /**
     * 设置登录token
     *
     * @param userId
     * @return
     */
    String setToken(BigInteger userId);

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    Boolean checkToken(String token);
}
