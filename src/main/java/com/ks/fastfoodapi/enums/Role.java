package com.ks.fastfoodapi.enums;

import lombok.Getter;

@Getter
public enum Role {
    CUSTOMER(0),
    RESTAURANT_MANAGER(1),
    GENERAL_MANAGER(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

}
