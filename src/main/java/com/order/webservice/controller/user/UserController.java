package com.order.webservice.controller.user;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户API接口")
public class UserController {

    @GetMapping("/login/{userId}/{password}")
    @ApiOperation(value = "用户登陆")
    public String login(@ApiParam(value = "用户ID(账号)", required = true)
                        @PathVariable(value = "userId") String userId,
                        @ApiParam(value = "密码", required = true)
                        @PathVariable(value = "password") String password) {

        StringBuilder sb = new StringBuilder();
        sb.append("你这用户ID和密码还想登陆？").append("自己开发去！");
        return sb.toString();
    }
}
