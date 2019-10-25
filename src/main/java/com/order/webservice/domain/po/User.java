package com.order.webservice.domain.po;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private LocalDateTime createTime;

    private LocalDateTime lastTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getOldPortrait() {
        return oldPortrait;
    }

    public void setOldPortrait(String oldPortrait) {
        this.oldPortrait = oldPortrait;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }
}
