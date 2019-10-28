package com.order.webservice.domain.po.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6125297654796395674L;

    private Integer id;

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
