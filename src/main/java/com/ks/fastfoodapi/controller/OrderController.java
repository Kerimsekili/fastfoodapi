package com.ks.fastfoodapi.controller;

import com.ks.fastfoodapi.dto.OrderDto;
import com.ks.fastfoodapi.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.create(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        Optional<OrderDto> orderDtoOptional = orderService.getOrderById(id);
        return orderDtoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/getByCustomer/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Long id) {
        List<OrderDto> orders = orderService.getAllByUserId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update/{id}/{role}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id,@PathVariable String role, @RequestBody OrderDto orderDto) {
        try {
            if (role.equals("GENERAL_MANAGER") || role.equals("RESTAURANT_MANAGER")) {
                orderService.update(id, orderDto);
                return ResponseEntity.ok(orderDto);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}/{role}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id, @PathVariable String role) {
        try {
            if (role.equals("GENERAL_MANAGER")) {
                orderService.delete(id);
                return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You are not allowed to delete Restaurant", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

