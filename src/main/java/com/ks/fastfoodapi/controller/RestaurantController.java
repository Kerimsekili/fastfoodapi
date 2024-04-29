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

    @PostMapping("/create/{role}")
    public ResponseEntity<String> create(@RequestBody RestaurantAddRequirements restaurantDto,@PathVariable String role) {
        try {
            if(role.equals("GENERAL_MANAGER")){
                restaurantService.create(restaurantDto);
                return new ResponseEntity<>("Restaurant updated successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("You are not allowed to create new Restaurant", HttpStatus.UNAUTHORIZED);
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}/{role}")
    public ResponseEntity<String> update(@PathVariable Long id, @PathVariable String role , @RequestBody RestaurantAddRequirements restaurantDto) {
        try {
            if (role.equals("GENERAL_MANAGER") || role.equals("RESTAURANT_MANAGER"))  {
                restaurantService.update(id, restaurantDto);
                return new ResponseEntity<>("Restaurant updated successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("You are not allowed to update Restaurant", HttpStatus.UNAUTHORIZED);
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}/{role}")
    public ResponseEntity<String> delete(@PathVariable Long id, @PathVariable String role) {
        try {
            if (role.equals("GENERAL_MANAGER")) {
                restaurantService.delete(id);
                return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("You are not allowed to update Restaurant", HttpStatus.UNAUTHORIZED);
            }
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
