package com.order.webservice.domain.po.product;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhangdingping at 2019/10/29
 */
@Data
public class Product {

    private Long id;

    private String name;

    private String detail;

    private String moreDetail;

    private String img;

    private Float price;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;
}
