package com.ks.fastfoodapi.service.restaurant;

import com.ks.fastfoodapi.dto.RestaurantDto;
import com.ks.fastfoodapi.model.Restaurant;
import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.RestaurantRepository;
import com.ks.fastfoodapi.repository.UserRepository;
import com.ks.fastfoodapi.enums.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public RestaurantServiceImpl create(RestaurantDto restaurantDto) {
        try {
            User manager = userRepository.findById(restaurantDto.getManagerId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + restaurantDto.getManagerId()));

            if (manager.getRole() != Role.RESTAURANT_MANAGER) {
                throw new IllegalArgumentException("Selected user is not a restaurant manager");
            }

            Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);

            restaurant.setManager(manager);

            restaurantRepository.save(restaurant);

            return this;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public RestaurantServiceImpl update(Long id, RestaurantDto restaurantDto) {
        Optional<Restaurant> existingRestaurantOptional = restaurantRepository.findById(id);
        if (existingRestaurantOptional.isPresent()) {
            try {
                User manager = userRepository.findById(restaurantDto.getManagerId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + restaurantDto.getManagerId()));

                if (manager.getRole() != Role.RESTAURANT_MANAGER) {
                    throw new IllegalArgumentException("Selected user is not a restaurant manager");
                }

                Restaurant existingRestaurant = existingRestaurantOptional.get();
                existingRestaurant.setName(restaurantDto.getName());
                existingRestaurant.setAddress(restaurantDto.getAddress());
                existingRestaurant.setManager(manager);

                restaurantRepository.save(existingRestaurant);

                return this;
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Restaurant not found with ID: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            restaurantRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Restaurant not found with ID: " + id);
        }
    }

    public List<RestaurantDto> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());
    }
}
