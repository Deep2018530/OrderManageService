package com.order.webservice.domain.vo.BillVo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BillNewVo {

    @ApiModelProperty("收入(充值/退款)")
    private Float income;

    @ApiModelProperty("支出")
    private Float expend;

    @ApiModelProperty("余额")
    private Float balance;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate date;

    private List<BillNewDetailVo> billNewDetailVoList;

}
