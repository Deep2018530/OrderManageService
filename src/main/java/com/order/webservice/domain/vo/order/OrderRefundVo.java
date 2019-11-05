package com.order.webservice.domain.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单")
public class OrderRefundVo {

    @ApiModelProperty("主订单ID")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("总金额")
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

    @ApiModelProperty("申请退款拒绝原因（审核拒绝原因）")
    private String verifyRejectReason;

    @ApiModelProperty("子订单ID")
    private BigInteger orderId;

    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("单价")
    private Float unitPrice;

    @ApiModelProperty("商品名称")
    private String Name;

    @ApiModelProperty("商品概述")
    private String detail;

    @ApiModelProperty("商品详细描述")
    private String moreDetail;

    @ApiModelProperty("用户名")
    private String nickName;

    @ApiModelProperty("手机号")
    private String mobile;

}
