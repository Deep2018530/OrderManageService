package com.order.webservice.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "订单")
public class OrderDto {

    @ApiModelProperty("订单号")
    private BigInteger id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String nickName;

    @ApiModelProperty("订单状态")
    private String status;

    @ApiModelProperty("金额上限")
    private Float amountUp;

    @ApiModelProperty("金额下限")
    private Float amountDown;

    @ApiModelProperty("起始时间")
    private LocalDateTime createTimeStart;

    @ApiModelProperty("结束时间")
    private LocalDateTime createTimeEnd;

    @ApiModelProperty("商品名称")
    private String productName;

}
