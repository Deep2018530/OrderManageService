package com.order.webservice.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.user.Role;

/**
 * created by zhangdingping on 2019/10/30
 */
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 管理员角色
     */
    long ADMIN = 1L;

    /**
     * 普通用户角色
     */
    long COMMON_USER = 2L;
}
