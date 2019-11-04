package com.order.webservice.mapper.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.account.Account;
import com.order.webservice.domain.po.account.UserAccount;
import org.apache.ibatis.annotations.Param;

/**
 * created by zhangdingping on 2019/10/31
 */
public interface AccountDao extends BaseMapper<Account> {
    UserAccount getUserAccountInfo(@Param("userId") Long userId);

}
