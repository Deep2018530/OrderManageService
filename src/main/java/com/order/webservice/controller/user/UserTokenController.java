package com.order.webservice.controller.user;

import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.service.user.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by zhangdingping at 2019/10/29
 */
@RestController
@RequestMapping(value = "/token")
@Api(value = "用户TokenAPI接口")
public class UserTokenController {

    @Autowired
    UserTokenService userTokenService;

    @GetMapping("/check/{token}")
    @ApiOperation(value = "token校验")
    public HttpResult<Boolean> check(@PathVariable(value = "token") String token) {

        return HttpResult.success(userTokenService.checkToken(token));
    }
}
