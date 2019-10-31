package com.order.webservice.domain.vo.user;

import com.order.webservice.domain.po.user.UserRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
@ApiModel(description = "用户")
public class UserVo {

    @ApiModelProperty("昵称/用户名")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("联系电话(手机)")
    private String mobile;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("曾经用过的头像")
    private String[] oldPortrait;

    @ApiModelProperty("地区(北京市朝阳区)")
    private String address;


    @ApiModelProperty("角色")
    private List<UserRole> userRoles;

    @ApiModelProperty("token")
    private String token;

}
