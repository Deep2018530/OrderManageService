package com.order.webservice.domain.vo.BillVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "账单")
public class BillVo {

    @ApiModelProperty("收入(充值/退款)")
    private Long income;

    @ApiModelProperty("支出")
    private Long expend;

    @ApiModelProperty("价格")
    private Long price;

    @ApiModelProperty("余额")
    private Long balance;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("账单类型")
    private int billType;
}
