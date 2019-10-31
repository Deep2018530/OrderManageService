package com.order.webservice.controller.account;


import com.order.webservice.common.HttpResult;
import com.order.webservice.service.account.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping(value = "/recharge")
@Api(value = "充值记录API接口")
public class RechargeController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RechargeService rechargeService;

    @GetMapping("/{amount}")
    @ApiOperation(value = "充值")
    public HttpResult recharge(@ApiParam(value = "token") @RequestHeader("token") String token,
                               @ApiParam(value = "充值金额(请做正整数校验)", required = true)
                               @PathVariable(value = "amount") float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("充值金额有误！");
        }

        Object userId = redisTemplate.opsForValue().get(token);
        Objects.requireNonNull(userId);

        return HttpResult.success(rechargeService.recharge(Long.parseLong(userId.toString()), amount));

    }
}
