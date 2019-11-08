package com.order.webservice.domain.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;

/**
 * created by zhangdingping at 2019/10/23
 */

@Data
public class UserRole {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private BigInteger userId;

    private Long roleId;

    private String roleName;

    private String roleDescription;
}
