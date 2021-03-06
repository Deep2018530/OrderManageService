package com.order.webservice.service.account.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.order.webservice.domain.enums.BillType;
import com.order.webservice.domain.po.account.Account;
import com.order.webservice.mapper.account.AccountDao;
import com.order.webservice.service.account.RechargeService;
import com.order.webservice.service.bill.BillService;
import com.order.webservice.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.math.BigInteger;
import java.util.Objects;

/**
 * created by zhangdingping on 2019/10/31
 */
@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private BillService billService;

    /**
     * 充值
     *
     * @param userId
     * @param amount
     */
    @Override
    public Boolean recharge(BigInteger userId, float amount) {

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Account account = accountDao.selectOne(queryWrapper);
        Objects.requireNonNull(account, "充值失败！账户不存在或被注销！account is null");

        Float balance = account.getBalance();
        account.setBalance(balance == null ? 0 : balance + amount);

        Float totalRecharge = account.getTotalRecharge();
        account.setTotalRecharge(totalRecharge == null ? 0 : totalRecharge + amount);

        accountDao.updateById(account);

        billService.createBill(null, account.getBalance(), amount, userId, BillType.RECHARGE);
        return true;
    }
}
