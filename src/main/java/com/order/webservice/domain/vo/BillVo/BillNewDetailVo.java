package com.order.webservice.domain.vo.BillVo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BillNewDetailVo {


    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("账单类型")
    private String billType;

    private Float balance;

    @ApiModelProperty("价格")
    private Float price;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate date;

    @ApiModelProperty("是否是收入，否则是支出，前端根据这个boolean值决定价格前用加号还是减号")
    private Boolean isIncome;
}
