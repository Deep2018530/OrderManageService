package com.order.webservice.domain.po.account;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhangdingping on 2019/10/31
 */
@Data
public class Account {

    private Long id;

    private Long userId;

    private Float balance;

    private Float totalRecharge;

    private Float totalConsumption;

    private Float arrearsWarn;

    private Boolean arrearsWarnStatus;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;
}
