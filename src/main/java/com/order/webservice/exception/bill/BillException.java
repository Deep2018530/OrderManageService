package com.order.webservice.exception.bill;

import com.order.webservice.exception.AbstractBillException;
import com.order.webservice.exception.AbstractCustomizeException;
import com.order.webservice.exception.IBillErrorde;
import com.order.webservice.exception.ICustomizeErrorde;

public class BillException extends AbstractBillException {

    public BillException(IBillErrorde errorde) {
        super(errorde);
    }
}
