package com.ks.fastfoodapi.service.restaurant;

import com.ks.fastfoodapi.dto.RestaurantDto;
import com.ks.fastfoodapi.model.Restaurant;
import com.ks.fastfoodapi.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantService create(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        restaurantRepository.save(restaurant);
        return this;
    }

    @Override
    public List<RestaurantDto> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private RestaurantDto mapToDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDto.class);
    }
}
