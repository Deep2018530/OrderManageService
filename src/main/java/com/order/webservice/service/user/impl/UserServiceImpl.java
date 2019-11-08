package com.order.webservice.service.user.impl;

import com.order.webservice.common.utils.OrderIdFactory;
import com.order.webservice.domain.dto.user.UserDto;
import com.order.webservice.domain.po.user.Role;
import com.order.webservice.domain.po.user.User;
import com.order.webservice.domain.po.user.UserRole;
import com.order.webservice.domain.vo.user.UserVo;
import com.order.webservice.exception.user.UserErrorCode;
import com.order.webservice.exception.user.UserException;
import com.order.webservice.mapper.user.RoleDao;
import com.order.webservice.mapper.user.UserDao;
import com.order.webservice.mapper.user.UserRoleDao;
import com.order.webservice.service.account.AccountService;
import com.order.webservice.service.user.UserService;
import com.order.webservice.service.user.UserTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * created by zhangdingping at 2019/10/23
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderIdFactory orderIdFactory;

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
        UserVo ans = user2Vo(user);
        return ans;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserVo regist(UserDto userDto) {
        if (ObjectUtils.isEmpty(userDto)) throw new IllegalArgumentException("输入参数有误！");

        //用户
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setId(orderIdFactory.createId("user"));
        userDao.insert(user);

        //初始化角色
        UserRole userRole = new UserRole();
        userRole.setUserId(Objects.requireNonNull(user.getId(), "新增用户失败！userId=null"));
        userRole.setRoleId(RoleDao.COMMON_USER);

        Role role = roleDao.selectById(RoleDao.COMMON_USER);
        Objects.requireNonNull(role, "赋予角色失败！role=null,roleId=" + RoleDao.COMMON_USER);
        userRoleDao.insert(userRole);

        //初始化账户
        accountService.initAccount(user.getId());

        return merge2Vo(user, userRole);
    }

    private UserVo merge2Vo(User user, UserRole userRole) {
        Objects.requireNonNull(user);
        UserVo ans = user2Vo(user);
        ans.setUserRoles(Arrays.asList(userRole));
        return ans;
    }

    private UserVo user2Vo(User user) {
        Objects.requireNonNull(user);
        UserVo ans = new UserVo();
        BeanUtils.copyProperties(user, ans);
        Boolean gender = user.getGender();
        if (!Objects.isNull(gender)) {
            ans.setGender(gender.equals(Boolean.TRUE) ? "男" : "女");
        }
        String token = userTokenService.setToken(user.getId());
        ans.setToken(token);
        return ans;
    }
}
