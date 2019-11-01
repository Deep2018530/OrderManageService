package com.order.webservice.domain.enums;

import java.util.List;

public interface IEnum<T> {

    String getCode();

    String getDescription();

    default List<String> getDescriptions() {
        return null;
    }

    default List<String> getCodes() {
        return null;
    }
}
