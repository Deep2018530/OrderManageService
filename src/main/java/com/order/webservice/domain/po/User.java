package com.order.webservice.domain.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
public class User {

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

    private LocalDateTime createTime;

    private LocalDateTime lastTime;
}
