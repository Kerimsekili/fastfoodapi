package com.ks.fastfoodapi.enums;

public enum OrderStatus {
    ORDER_RECEIVED(0),
    PREPARED(1),
    SET_OUT(2),
    DELIVERED(3);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }
}
