package com.ks.fastfoodapi.dto;

import com.ks.fastfoodapi.model.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {

    private Long id;

    private String name;

    private String address;

    private UserResponse manager;
}
