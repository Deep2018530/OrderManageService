package com.order.webservice.domain.po.bill;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Detail {

    //明细id
    private Long id;

    //账单id
    private Long BillId;

    //产品Id
    private Long productId;

    //数量
    private int num;

    //价格
    private Long price;

    //余额
    private Long balance;

    //账单类型
    private int billType;

    //账单生成时间
    private LocalDateTime createTime;

    //最后修改时间
    private LocalDateTime lastTime;
}
