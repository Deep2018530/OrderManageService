package com.order.webservice.service.user;

import com.order.webservice.domain.dto.user.UserDto;
import com.order.webservice.domain.vo.user.UserVo;

/**
 * created by zhangdingping at 2019/10/23
 */
public interface UserService {

    /**
     * 用户登陆
     *
     * @param email
     * @param password
     * @return
     */
    UserVo login(String email, String password);

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     */
    UserVo regist(UserDto userDto);
}
