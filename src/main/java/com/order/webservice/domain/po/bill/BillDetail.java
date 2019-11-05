package com.order.webservice.domain.po.bill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class BillDetail {


    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    private BigInteger billId;

    private Long productId;

    private Integer num;

    private Float price;

    private Float balance;

    private String billType;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;

}
