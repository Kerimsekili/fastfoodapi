package com.ks.fastfoodapi.service.restaurant;

import com.ks.fastfoodapi.requirements.RestaurantAddRequirements;
import com.ks.fastfoodapi.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantServiceImpl create(RestaurantAddRequirements restourantAddReq);
    RestaurantServiceImpl update(Long id, RestaurantAddRequirements restourantAddReq);
    void delete(Long id);
    List<RestaurantDto> getAll();

}
