package com.order.webservice.domain.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * created by zhangdingping on 2019/11/2
 */
@ApiModel(description = "订单明细")
public class OrderDetailNewVo {

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("单价")
    private Float price;
}
