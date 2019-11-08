package com.order.webservice.exception.bill;

import com.order.webservice.exception.IBillErrorde;

public enum BillErrorCode implements IBillErrorde {

    BILL_NOT_EXIST("6003", "账单不存在！");

    private String code;

    private String message;

    BillErrorCode(String code, String message) {
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
