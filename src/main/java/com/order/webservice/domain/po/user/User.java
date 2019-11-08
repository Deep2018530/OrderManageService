package com.order.webservice.domain.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
public class User {

    @TableId(value = "id", type = IdType.INPUT)
    private BigInteger id;

    private String nickName;

    private String password;

    /**
     * true 男 false nv
     */
    private Boolean gender;

    private String email;

    private String mobile;

    private String portrait;

    private String oldPortrait;

    private String address;

    private LocalDateTime createTime;

    private LocalDateTime lastTime;

    private List<UserRole> userRoles;


}
