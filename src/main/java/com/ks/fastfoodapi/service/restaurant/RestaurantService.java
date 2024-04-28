package com.ks.fastfoodapi.service.restaurant;

import com.ks.fastfoodapi.requirements.RestourantAddRequirements;
import com.ks.fastfoodapi.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantServiceImpl create(RestourantAddRequirements restourantAddReq);
    RestaurantServiceImpl update(Long id, RestourantAddRequirements restourantAddReq);
    void delete(Long id);
    List<RestaurantDto> getAll();

}
