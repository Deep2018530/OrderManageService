package com.order.webservice.domain.vo.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * created by zhangdingping at 2019/10/29
 */
@Data
@ApiModel(value = "商品")
public class ProductVo {

    @ApiModelProperty("商品ID")
    private long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品描述（概述）")
    private String detail;

    @ApiModelProperty("商品明细描述")
    private String moreDetail;

    @ApiModelProperty("商品图片")
    private String img;

    @ApiModelProperty("商品价格（单价）")
    private float price;

    @ApiModelProperty("商品创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
