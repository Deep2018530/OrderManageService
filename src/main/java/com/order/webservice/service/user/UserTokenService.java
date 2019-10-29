package com.order.webservice.service.user;

public interface UserTokenService {

    /**
     * 设置登录token
     *
     * @param userId
     * @return
     */
    String setToken(Long userId);

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    Boolean checkToken(String token);
}
