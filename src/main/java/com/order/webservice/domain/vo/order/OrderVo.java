package com.order.webservice.domain.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单")
public class OrderVo {

    @ApiModelProperty("订单号")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long user_id;

    @ApiModelProperty("金额")
    private Double amount;

    @ApiModelProperty("创建时间")
    private LocalDateTime create_time;

    @ApiModelProperty("支付时间")
    private LocalDateTime pay_time;

    @ApiModelProperty("订单状态")
    private String status;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime last_time;

}
