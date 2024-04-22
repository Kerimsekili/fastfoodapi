package com.ks.fastfoodapi.service.order;

import com.ks.fastfoodapi.dto.OrderDto;

public interface OrderService {
    OrderDto create(OrderDto orderDto);
    OrderDto update(Long id, OrderDto orderDto);
    void delete(Long id);
}

