package com.order.webservice.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * created by zhangdingping at 2019/10/23
 */
public interface UserDao extends BaseMapper<User> {

    User selectOne(@Param("email") String email);

}
