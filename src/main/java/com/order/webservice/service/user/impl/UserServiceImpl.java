package com.order.webservice.service.user.impl;

import com.order.webservice.common.utils.TokenUtils;
import com.order.webservice.domain.po.user.User;
import com.order.webservice.domain.vo.TokenVo;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.exception.user.UserException;
import com.order.webservice.mapper.user.UserDao;
import com.order.webservice.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * created by zhangdingping at 2019/10/23
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserVo login(String email, String password) {
        User user = userDao.selectOne(email);
        if (Objects.isNull(user)) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            throw new UserException(UserErrorCode.PASSWORD_WRONG);
        }
        TokenVo tokenVo = new TokenVo();
        tokenVo.setEmail(email);

        UserVo ans = new UserVo();
        ans.setToken(TokenUtils.createToken(tokenVo));
        BeanUtils.copyProperties(user, ans);
        return ans;
    }
}
