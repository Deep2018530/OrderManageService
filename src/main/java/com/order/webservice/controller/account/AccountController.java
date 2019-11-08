package com.order.webservice.controller.account;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.service.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/account")
@Api(value = "账户API接口")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getUserAccountInfo/{userId}")
    @ApiOperation(value = "获取账户所有信息")
    public HttpResult<AccountVo> getUserAccountInfo(@RequestHeader("token") String token,
                                                    @ApiParam(value = "用户Id") @PathVariable(value = "userId") Long userId) {
        Object userIdObj = redisTemplate.opsForValue().get(token);
        Objects.requireNonNull(userIdObj, UserErrorCode.USER_NOT_EXIST.getMessage());
        return HttpResult.success(accountService.getUserAccountInfo(new BigInteger(userIdObj.toString())));
    }
}