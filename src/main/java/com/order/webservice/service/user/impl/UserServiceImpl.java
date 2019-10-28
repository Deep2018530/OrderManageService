package com.order.webservice.service.user.impl;

import com.order.webservice.domain.po.user.User;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.exception.user.UserException;
import com.order.webservice.mapper.user.UserDao;
import com.order.webservice.service.user.UserService;
import com.order.webservice.service.user.UserTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * created by zhangdingping at 2019/10/23
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserVo login(String email, String password) {
        User user = userDao.selectOne(email);
        if (Objects.isNull(user)) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            throw new UserException(UserErrorCode.PASSWORD_WRONG);
        }
        UserVo ans = new UserVo();
        BeanUtils.copyProperties(user, ans);
        Boolean gender = user.getGender();
        if (!Objects.isNull(gender)) {
            ans.setGender(gender == Boolean.TRUE ? "男" : "女");
        }
        String token = userTokenService.setToken(user.getId());
        ans.setToken(token);
        return ans;
    }
}
