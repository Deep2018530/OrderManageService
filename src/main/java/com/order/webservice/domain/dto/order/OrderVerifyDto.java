package com.order.webservice.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;

/**
 * created by zhangdingping on 2019/11/3
 */

@Data
@ApiModel(description = "订单校验DTO")
public class OrderVerifyDto {

    @ApiModelProperty("订单ID")
    private BigInteger orderId;

    @ApiModelProperty("用户ID")
    private BigInteger userId;

    @ApiModelProperty("是否审核通过")
    private Boolean verifyStatus;

    @ApiModelProperty("审核不通过的原因描述")
    private String rejectReason;
}
