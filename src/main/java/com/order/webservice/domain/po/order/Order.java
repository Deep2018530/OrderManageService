package com.order.webservice.domain.po.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@TableName(value = "`order`")
public class Order {

    //订单ID
    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    //用户ID
    private Long userId;

    //订单总金额
    private Float amount;

    //订单创建时间
    private LocalDateTime createTime;

    //订单支付时间
    private LocalDateTime payTime;

    //订单状态
    private String status;

    //最后修改时间
    private LocalDateTime lastTime;

    /**
     * 申请退款拒绝原因（审核拒绝原因）
     */
    private String verifyRejectReason;

}
