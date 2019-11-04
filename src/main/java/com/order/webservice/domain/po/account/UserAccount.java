package com.order.webservice.domain.po.account;

import lombok.Data;

@Data
public class UserAccount {

    //用户名/昵称
    private String nickName;

    //头像
    private String portrait;

    //邮箱
    private String email;

    //手机号
    private String mobile;

    //余额
    private Double balance;
}
