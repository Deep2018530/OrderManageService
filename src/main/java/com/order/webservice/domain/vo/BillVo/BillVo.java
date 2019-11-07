package com.order.webservice.domain.vo.BillVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@ApiModel(description = "账单")
public class BillVo {


    @ApiModelProperty("收入(充值/退款)")
    private Float income;

    @ApiModelProperty("支出")
    private Float expend;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate date;

    private List<BillDetailVo> billDetail;

    //@ApiModelProperty("价格")
    //private Long price;

    //@ApiModelProperty("余额")
    //private Long balance;

    //@ApiModelProperty("商品名称")
    //private String productName;


}
