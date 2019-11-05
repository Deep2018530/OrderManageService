package com.order.webservice.domain.po.bill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class Bill {

    //票据id
    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    //用户id
    private Long userId;

    //收入(充值/退款)
    private Float income;

    //支出
    private Float expend;

    //账单生成时间
    private LocalDateTime createTime;

    //最后修改时间
    private LocalDateTime lastTime;


}
