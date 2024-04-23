package com.ks.fastfoodapi.enums;

import lombok.Getter;

@Getter
public enum ProductName {
    HAMBURGER_MENU(0),
    PIZZA_MENU(1),
    DONER_MENU(2);

    private final int value;

    ProductName(int value) {
        this.value = value;
    }
}
