package com.order.webservice.domain.po.order;

import com.sun.org.apache.bcel.internal.generic.FLOAD;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * created by zhangdingping on 2019/11/2
 */
@Data
public class OrderDetail {


    private Long id;

    private BigInteger orderId;

    private Long productId;

    private Integer num;

    private Float amount;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;

}
