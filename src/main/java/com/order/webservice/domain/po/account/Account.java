package com.order.webservice.domain.po.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * created by zhangdingping on 2019/10/31
 */
@Data
public class Account {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private BigInteger userId;

    private Float balance;

    private Float totalRecharge;

    private Float totalConsumption;

    private Float arrearsWarn;

    private Boolean arrearsWarnStatus;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;
}
