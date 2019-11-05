package com.order.webservice.domain.po.bill;

import lombok.Data;

@Data
public class BillDetail {

    //收入(充值/退款)
    private Long income;

    //支出
    private Long expend;

    //价格
    private Long price;

    //余额
    private Long balance;

    //商品名称
    private String productName;

    //账单类型
    private String billType;
}
