package com.order.webservice.domain.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by zhangdingping on 2019/11/2
 */
@Data
@ApiModel(description = "订单信息")
public class OrderNewVo {

    @ApiModelProperty("订单号")
    private BigInteger id;

    @ApiModelProperty("用户ID")
    private BigInteger userId;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("订单总金额")
    private Float amount;

    @ApiModelProperty("订单生成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty("订单状态")
    private String status;

    @ApiModelProperty("订单明细")
    private List<OrderDetailNewVo> orderDetailNewVoList;

}
