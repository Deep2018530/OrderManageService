package com.order.webservice.service.account;

/**
 * created by zhangdingping on 2019/10/31
 */
public interface RechargeService {

    /**
     * 充值
     *
     * @param amount
     */
    Boolean recharge(long userId, float amount);
}
