package com.order.webservice.service.account.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.order.webservice.domain.po.account.Account;
import com.order.webservice.mapper.account.AccountDao;
import com.order.webservice.service.account.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.Objects;

/**
 * created by zhangdingping on 2019/10/31
 */
@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 充值
     *
     * @param userId
     * @param amount
     */
    @Override
    public Boolean recharge(long userId, float amount) {

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Account account = accountDao.selectOne(queryWrapper);
        Objects.requireNonNull(account, "充值失败！账户不存在或被注销！account is null");

        Float balance = account.getBalance();
        account.setBalance(balance == null ? 0 : balance + amount);

        Float totalRecharge = account.getTotalRecharge();
        account.setTotalRecharge(totalRecharge == null ? 0 : totalRecharge + amount);

        accountDao.updateById(account);
        return true;
    }
}
