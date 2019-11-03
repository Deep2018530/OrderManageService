package com.order.webservice.controller.account;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.vo.account.AccountVo;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.service.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/account")
@Api(value = "账户API接口")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/getUserAccountInfo/{userId}")
    @ApiOperation(value = "获取账户所有信息")
    public HttpResult<AccountVo> getUserAccountInfo(@ApiParam(value = "用户Id") @PathVariable(value = "userId") Long userId) {
        return HttpResult.success(accountService.getUserAccountInfo(userId));
    }
}
