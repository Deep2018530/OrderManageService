package com.order.webservice.domain.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单")
public class OrderVo {

    @ApiModelProperty("订单号")
    private BigInteger id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String nickName;

    @ApiModelProperty("金额")
    private Double amount;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty("支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty("订单状态")
    private String status;

    @ApiModelProperty("最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastTime;

    @ApiModelProperty("商品名称")
    private String productName;

}
