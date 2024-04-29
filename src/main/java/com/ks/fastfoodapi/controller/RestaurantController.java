package com.ks.fastfoodapi.controller;

import com.ks.fastfoodapi.dto.RestaurantDto;
import com.ks.fastfoodapi.repository.UserRepository;
import com.ks.fastfoodapi.requirements.RestaurantAddRequirements;
import com.ks.fastfoodapi.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserRepository userRepository;

    //@PreAuthorize("hasRole('GENERAL_MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody RestaurantAddRequirements restaurantDto) {
        try {
            restaurantService.create(restaurantDto);
            return new ResponseEntity<>("Restaurant created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //@PreAuthorize("hasRole('GENERAL_MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody RestaurantAddRequirements restaurantDto) {
        try {
            restaurantService.update(id, restaurantDto);
            return new ResponseEntity<>("Restaurant updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //@PreAuthorize("hasRole('GENERAL_MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            restaurantService.delete(id);
            return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.OK);
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
