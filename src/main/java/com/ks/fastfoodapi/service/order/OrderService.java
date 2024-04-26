package com.ks.fastfoodapi.service.order;

import com.ks.fastfoodapi.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto create(OrderDto orderDto);
    OrderDto update(Long id, OrderDto orderDto);
    void delete(Long id);
    Optional<OrderDto> getOrderById(Long id);
    List<OrderDto> getAll();
}

