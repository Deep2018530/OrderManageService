package com.order.webservice.domain.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单")
public class OrderVo {

    @ApiModelProperty("订单号")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String nickName;

    @ApiModelProperty("金额")
    private Double amount;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("订单状态")
    private String status;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime lastTime;

    @ApiModelProperty("商品名称")
    private String productName;

}
