package com.ks.fastfoodapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {

    private Long id;

    private String name;

    private String address;

    private Long managerId;
}
