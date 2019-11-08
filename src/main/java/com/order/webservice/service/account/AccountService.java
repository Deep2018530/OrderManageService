package com.order.webservice.service.account;

import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.domain.vo.user.UserVo;

import java.math.BigInteger;
import java.util.List;

/**
 * created by zhangdingping on 2019/10/31
 */
public interface AccountService {

    /**
     * 初始化账户
     *
     * @param userId
     */
    void initAccount(BigInteger userId);

    AccountVo getUserAccountInfo(BigInteger userId);
}
