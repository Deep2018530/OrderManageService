package com.order.webservice.domain.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * created by zhangdingping on 2019/11/2
 */
@Data
@ApiModel(description = "订单明细")
public class OrderDetailNewVo {

    @ApiModelProperty("订单ID")
    private BigInteger orderId;

    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品概述")
    private String detail;

    @ApiModelProperty("商品详细描述")
    private String moreDetail;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("单价")
    private Float price;

    @ApiModelProperty("订单生成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
