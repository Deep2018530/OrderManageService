package com.order.webservice.service.account.impl;

import com.order.webservice.domain.po.account.Account;
import com.order.webservice.domain.po.account.UserAccount;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.mapper.account.AccountDao;
import com.order.webservice.service.account.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * created by zhangdingping on 2019/10/31
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountDao accountDao;

    /**
     * 初始化账户
     *
     * @param userId
     */
    @Override
    public void initAccount(Long userId) {
        if (Objects.isNull(userId)) throw new IllegalArgumentException("参数错误！userId is null");

        Account account = new Account();
        account.setUserId(userId);
        accountDao.insert(account);
    }

    @Override
    public AccountVo getUserAccountInfo(Long userId) {
        UserAccount userAccount = accountDao.getUserAccountInfo(userId);
        AccountVo accountVo = parseToAccountVo(userAccount);
        return accountVo;
    }

    private AccountVo parseToAccountVo(UserAccount userAccount) {
        Objects.requireNonNull(userAccount);
        AccountVo accountVo = new AccountVo();
        BeanUtils.copyProperties(userAccount, accountVo);
        return accountVo;
    }

}
