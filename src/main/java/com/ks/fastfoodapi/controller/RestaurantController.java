package com.ks.fastfoodapi.controller;

import com.ks.fastfoodapi.dto.RestaurantDto;
import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.UserRepository;
import com.ks.fastfoodapi.enums.Role;
import com.ks.fastfoodapi.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody RestaurantDto restaurantDto) {
        try {
            Optional<User> managerOptional = userRepository.findById(restaurantDto.getManagerId());

            if (managerOptional.isEmpty()) {
                throw new IllegalArgumentException("User not found with ID: " + restaurantDto.getManagerId());
            }
            User manager = managerOptional.get();
            if (manager.getRole() != Role.RESTAURANT_MANAGER) {
                throw new IllegalArgumentException("Selected user is not a restaurant manager");
            }
            restaurantService.create(restaurantDto);
            return new ResponseEntity<>("Restaurant created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
        try {
            restaurantService.update(id, restaurantDto);
            return new ResponseEntity<>("Restaurant updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.getAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
