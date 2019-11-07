package com.order.webservice.domain.po.bill;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class BillDetail {


    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    private BigInteger billId;

    private Long productId;

    private String name;

    private Integer num;

    private Float price;

    private Float balance;

    private String billType;

    private LocalDate date;

    //private LocalDateTime createTime;

    //private LocalDateTime lastTime;

}
