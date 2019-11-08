package com.order.webservice.exception.user;

import com.order.webservice.exception.ICustomizeErrorde;

public enum UserErrorCode implements ICustomizeErrorde {

    USER_NOT_EXIST("6001", "用户不存在！"),
    
    PASSWORD_WRONG("6002", "密码错误！");

    private String code;

    private String message;

    UserErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
