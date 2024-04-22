package com.ks.fastfoodapi.service.order;

import com.ks.fastfoodapi.dto.OrderDto;
import com.ks.fastfoodapi.model.Order;
import com.ks.fastfoodapi.model.Restaurant;
import com.ks.fastfoodapi.repository.OrderRepository;
import com.ks.fastfoodapi.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public OrderDto update(Long id, OrderDto orderDto) {
        // Fetch the existing order from the database
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));

        // Fetch the existing restaurant from the database based on the provided restaurantId
        Restaurant existingRestaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + orderDto.getRestaurantId()));

        // Set the existing restaurant to the order
        existingOrder.setRestaurant(existingRestaurant);

        // Update other fields of the existing order with the values from the DTO
        existingOrder.setProductName(orderDto.getProductName());
        existingOrder.setDeliveryAddress(orderDto.getDeliveryAddress());
        existingOrder.setCustomerName(orderDto.getCustomerName());
        existingOrder.setContactNumber(orderDto.getContactNumber());
        existingOrder.setStatus(orderDto.getStatus());

        // Save the updated order
        Order updatedOrder = orderRepository.save(existingOrder);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }


    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
