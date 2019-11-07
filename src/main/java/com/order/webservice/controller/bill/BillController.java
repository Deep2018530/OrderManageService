package com.order.webservice.controller.bill;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.vo.BillVo.BillVo;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.service.bill.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
@RequestMapping(value = "/bill")
@Api(value = "账单API接口")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/getBillDetailInfo/{date}")
    @ApiOperation(value = "查询账单明细")
    public HttpResult<BillVo> getBillDetailInfo(@ApiParam(value = "token") @RequestHeader(value = "token") String token,
                                                @ApiParam(value = "查询时间(格式:2019-11-07)") @PathVariable String date) {
        Object userId = redisTemplate.opsForValue().get(token);
        Objects.requireNonNull(userId, UserErrorCode.USER_NOT_EXIST.getMessage());
        return HttpResult.success(billService.getBillInfo(userId, date));
    }
}
