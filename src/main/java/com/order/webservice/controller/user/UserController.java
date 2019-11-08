package com.order.webservice.controller.user;


import com.order.webservice.common.HttpResult;
import com.order.webservice.domain.dto.user.UserDto;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户API接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login/{email}/{password}")
    @ApiOperation(value = "用户登陆")
    public HttpResult<UserVo> login(@ApiParam(value = "登陆邮箱", required = true, defaultValue = "admin@163.com")
                                    @PathVariable(value = "email") String email,
                                    @ApiParam(value = "密码", required = true, defaultValue = "admin")
                                    @PathVariable(value = "password") String password) {
        return HttpResult.success(userService.login(email, password));
    }

    @PostMapping("/regist")
    @ApiOperation(value = "用户注册")
    public HttpResult<UserVo> regist(@ApiParam(value = "用户注册对象") @RequestBody UserDto userDto) {
        return HttpResult.success(userService.regist(userDto));
    }
}
