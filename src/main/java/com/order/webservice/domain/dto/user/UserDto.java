package com.order.webservice.domain.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
public class UserDto {


    private String nickName;

    private String password;

    private Boolean gender;

    private String email;

    private String mobile;

    private String portrait;

    private String[] oldPortrait;

    private String address;


    private String roleName;

    private String token;
}
