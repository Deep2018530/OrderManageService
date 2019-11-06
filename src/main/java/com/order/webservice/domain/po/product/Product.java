package com.order.webservice.domain.po.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhangdingping at 2019/10/29
 */
@Data
public class Product {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    //产品名称
    private String name;
    //商品概要描述
    private String detail;
    //商品明细描述
    private String moreDetail;
    //图片
    private String img;
    //单价
    private Float price;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;

}
