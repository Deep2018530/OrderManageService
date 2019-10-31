package com.order.webservice.domain.dto.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
@ApiModel(value = "用户")
public class UserDto {


    @ApiModelProperty("用户昵称/用户名")
    private String nickName;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("性别")
    private Boolean gender;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("地区(北京市朝阳区)")
    private String address;
}
