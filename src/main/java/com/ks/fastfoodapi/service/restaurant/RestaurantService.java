package com.ks.fastfoodapi.service.restaurant;

import com.ks.fastfoodapi.dto.RestaurantDto;
import com.ks.fastfoodapi.dto.UserDto;

import java.util.List;

public interface RestaurantService {
    RestaurantService create(RestaurantDto restaurantDto);
    List<RestaurantDto> getAll();

}
