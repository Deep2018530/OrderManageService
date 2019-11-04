package com.order.webservice.domain.vo.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "账户")
public class AccountVo {

    @ApiModelProperty("昵称/用户名")
    private String nickName;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("联系电话(手机)")
    private String mobile;

    @ApiModelProperty("余额")
    private Double balance;
}
