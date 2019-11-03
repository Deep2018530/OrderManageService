package com.order.webservice.domain.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by zhangdingping on 2019/11/3
 */

@Data
@ApiModel(description = "订单状态统计")
public class OrderStatisticsVo {

    @ApiModelProperty("已审核")
    private Integer hasVerify;

    @ApiModelProperty("未审核")
    private Integer notVerify;
}
