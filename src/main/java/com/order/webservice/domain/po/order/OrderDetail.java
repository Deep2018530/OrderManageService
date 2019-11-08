package com.order.webservice.domain.po.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * created by zhangdingping on 2019/11/2
 */
@Data
@TableName(value = "orderDetail")
public class OrderDetail {

    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    //订单id
    private BigInteger orderId;

    //商品id
    private Long productId;

    //数量
    private Integer num;

    //单价
    private Float amount;

    //创建时间
    private LocalDateTime createTime;

    //最后修改时间
    private LocalDateTime lastTime;

}
