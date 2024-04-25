package com.ks.fastfoodapi.service.restaurant;

import com.ks.fastfoodapi.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantServiceImpl create(RestaurantDto restaurantDto);
    RestaurantServiceImpl update(Long id, RestaurantDto restaurantDto);
    void delete(Long id);
    List<RestaurantDto> getAll();

}
