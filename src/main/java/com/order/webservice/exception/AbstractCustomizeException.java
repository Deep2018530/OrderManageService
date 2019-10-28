package com.order.webservice.exception;

import lombok.Data;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
public abstract class AbstractCustomizeException extends RuntimeException {

    private String code;

    private String message;

    public AbstractCustomizeException(ICustomizeErrorde errorde) {
        this.code = errorde.getCode();
        this.message = errorde.getMessage();
    }
}
