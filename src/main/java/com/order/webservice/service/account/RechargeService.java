package com.order.webservice.service.account;

import java.math.BigInteger;

/**
 * created by zhangdingping on 2019/10/31
 */
public interface RechargeService {

    /**
     * 充值
     *
     * @param amount
     */
    Boolean recharge(BigInteger userId, float amount);
}
