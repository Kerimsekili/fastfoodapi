package com.ks.fastfoodapi.initializer;

import com.ks.fastfoodapi.dto.OrderDto;
import com.ks.fastfoodapi.enums.OrderStatus;
import com.ks.fastfoodapi.enums.ProductName;
import com.ks.fastfoodapi.enums.Role;
import com.ks.fastfoodapi.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class OrderInitializer implements CommandLineRunner {

    private final OrderService orderService;

    @Autowired
    public OrderInitializer(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {
        List<OrderDto> initialOrders = createInitialOrders();
        initialOrders.forEach(orderService::create);
    }

    private List<OrderDto> createInitialOrders() {
        List<OrderDto> orders = new ArrayList<>();
        String[] customerNames = {"kerim", "ata", "zeynep", "kaan"};
        for (int i = 0; i < customerNames.length; i++) {
            OrderDto order = new OrderDto();
            order.setProductName(ProductName.HAMBURGER_MENU); // Set product name as needed
            order.setRestaurantId(1L); // Assuming the restaurant ID
            order.setDeliveryAddress("Delivery Address " + (i + 1));
            order.setCustomerName(customerNames[i]);
            order.setContactNumber("0555" + String.format("%07d", i + 1)); // Assuming Turkish phone number format
            order.setOrderStatus(OrderStatus.ORDER_RECEIVED);
            order.setRole(Role.CUSTOMER);
            order.setCustomerId((long) (i + 1)); // Assuming customer IDs start from 1
            orders.add(order);
        }
        return orders;
    }
}
