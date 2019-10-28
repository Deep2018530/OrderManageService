package com.order.webservice.domain.po.user;

import lombok.Data;

/**
 * created by zhangdingping at 2019/10/23
 */

@Data
public class UserRole {

    private Long id;

    private Long userId;

    private Long roleId;

    private String roleName;

    private String roleDescription;
}
