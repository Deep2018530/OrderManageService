package com.order.webservice.domain.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * created by zhangdingping at 2019/11/1
 */
public enum OrderStatus implements IEnum<OrderStatus> {

    NOT_PAY("0", "待支付"),

    FINISHED("1", "已完成"),

    APPLY_FOR_REFUND("2", "申请退款"),

    REFUSED_FOR_REFUND("3", "拒绝退款");

    OrderStatus(String code, String description) {
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

    public static OrderStatus getByCode(String code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static List<String> getAllDescription() {
        List<String> ans = new ArrayList<>();
        for (OrderStatus value : OrderStatus.values()) {
            ans.add(value.description);
        }
        return ans;
    }
}
