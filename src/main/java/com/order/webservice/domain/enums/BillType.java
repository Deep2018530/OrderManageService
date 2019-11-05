package com.order.webservice.domain.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zhangdingping at 2019/11/5
 */
public enum BillType implements IEnum<BillType> {

    BUY("0", "商品购买"),

    REFUND("1", "退款"),

    RECHARGE("2", "充值");

    BillType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;

    private String description;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static BillType getByCode(String code) {
        for (BillType value : BillType.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static List<String> getAllDescription() {
        List<String> ans = new ArrayList<>();
        for (BillType value : BillType.values()) {
            ans.add(value.description);
        }
        return ans;
    }
}
