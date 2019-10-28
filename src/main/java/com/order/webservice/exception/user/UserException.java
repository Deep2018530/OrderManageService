package com.order.webservice.exception.user;

import com.order.webservice.exception.AbstractCustomizeException;
import com.order.webservice.exception.ICustomizeErrorde;

/**
 * created by zhangdingping at 2019/10/23
 */
public class UserException extends AbstractCustomizeException {
    
    public UserException(ICustomizeErrorde errorde) {
        super(errorde);
    }
}
