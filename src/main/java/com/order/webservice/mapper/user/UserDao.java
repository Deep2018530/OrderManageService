package com.order.webservice.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.webservice.domain.po.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * created by zhangdingping at 2019/10/23
 */
public interface UserDao extends BaseMapper<User> {

    User selectOne(@Param("email") String email);

    @Select(value = "SELECT * FROM user WHERE id = #{userId}")
    User findById(@Param(value = "userId") Long userId);

    User selectOneById(@Param("id") Long id);

    User selectOneByName(@Param("nick_name") String nick_name);

}
