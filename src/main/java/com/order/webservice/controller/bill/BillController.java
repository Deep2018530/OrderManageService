package com.order.webservice.controller.bill;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.vo.BillVo.BillVo;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.service.bill.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/bill")
@Api(value = "账单API接口")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/getBillDetailInfo/{userId}/{month}")
    @ApiOperation(value = "查询账单明细")
    public HttpResult<BillVo> getBillDetailInfo(@ApiParam(value = "用户Id") @PathVariable(value = "userId") Long userId,
                                                @ApiParam(value = "查询时间(精确到月份,格式:201901)") @PathVariable String month) {

        return HttpResult.success(billService.getBillDetailInfo(userId, month));
    }
}
