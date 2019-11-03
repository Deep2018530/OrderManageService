package com.order.webservice.exception;

import lombok.Data;

/**
 * created by zhangdingping at 2019/10/23
 */
@Data
public abstract class AbstractBillException extends RuntimeException {

    private String code;

    private String message;


    public AbstractBillException(IBillErrorde errorde) {
        this.code = errorde.getCode();
        this.message = errorde.getMessage();
    }
}
