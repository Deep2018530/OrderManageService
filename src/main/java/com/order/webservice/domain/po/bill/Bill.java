package com.order.webservice.domain.po.bill;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bill {

    //票据id
    private Long id;

    //用户id
    private Long userId;

    //收入(充值/退款)
    private Long income;

    //支出
    private Long expend;

    //账单生成时间
    private LocalDateTime createTime;

    //最后修改时间
    private LocalDateTime lastTime;



}
