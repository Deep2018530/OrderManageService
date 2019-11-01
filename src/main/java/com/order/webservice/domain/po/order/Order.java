package com.order.webservice.domain.po.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {

    //订单ID
    private Long id;
    //用户ID
    private Long user_id;
    //订单总金额
    private Double amount;
    //订单创建时间
    private LocalDateTime create_time;
    //订单支付时间
    private LocalDateTime pay_time;
    //订单状态
    private String status;
    //最后修改时间
    private LocalDateTime last_time;

}
