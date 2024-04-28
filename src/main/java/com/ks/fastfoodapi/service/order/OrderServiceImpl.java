package com.ks.fastfoodapi.service.order;

import com.ks.fastfoodapi.dto.OrderDto;
import com.ks.fastfoodapi.enums.OrderStatus;
import com.ks.fastfoodapi.enums.ProductName;
import com.ks.fastfoodapi.model.Order;
import com.ks.fastfoodapi.model.Restaurant;
import com.ks.fastfoodapi.model.User;
import com.ks.fastfoodapi.repository.OrderRepository;
import com.ks.fastfoodapi.repository.RestaurantRepository;
import com.ks.fastfoodapi.repository.UserRepository;
import com.ks.fastfoodapi.enums.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public OrderDto create(OrderDto orderDto) {
        // Validate product name
        if (!isValidProductName(orderDto.getProductName())) {
            throw new IllegalArgumentException("Invalid product name");
        }

        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + orderDto.getRestaurantId()));

        User customer = userRepository.findByUsername(orderDto.getCustomerName())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with name: " + orderDto.getCustomerName()));

        if (customer.getRole() != Role.CUSTOMER) {
            throw new IllegalArgumentException("User " + customer.getUsername() + " does not have the role of CUSTOMER");
        }

        orderDto.setOrderStatus(OrderStatus.ORDER_RECEIVED);

        Order order = modelMapper.map(orderDto, Order.class);
        order.setRestaurant(restaurant);
        order.setCustomer(customer);
        Order savedOrder = orderRepository.save(order);

        return modelMapper.map(savedOrder, OrderDto.class);
    }



    @Override
    public OrderDto update(Long id, OrderDto orderDto) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

            if (orderDto.getOrderStatus() != null) {
                existingOrder.setStatus(orderDto.getOrderStatus());
            }

            if (orderDto.getProductName() != null) {
                existingOrder.setProductName(orderDto.getProductName());
            }
            if (orderDto.getRestaurantId() != null) {
                Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                        .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + orderDto.getRestaurantId()));
                existingOrder.setRestaurant(restaurant);
            }
            if (orderDto.getDeliveryAddress() != null) {
                existingOrder.setDeliveryAddress(orderDto.getDeliveryAddress());
            }
            if (orderDto.getCustomerName() != null) {
                existingOrder.setCustomerName(orderDto.getCustomerName());
            }
            if (orderDto.getContactNumber() != null) {
                existingOrder.setContactNumber(orderDto.getContactNumber());
            }

            Order updatedOrder = orderRepository.save(existingOrder);
            return modelMapper.map(updatedOrder, OrderDto.class);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllByUserId(Long userId) {
    List<Order> orders = orderRepository.findAllByCustomer_Id(userId);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());  }

    public Optional<OrderDto> getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.map(order -> modelMapper.map(order, OrderDto.class));
    }


    private boolean isValidProductName(ProductName productName) {
        for (ProductName validProductName : ProductName.values()) {
            if (validProductName.equals(productName)) {
                return true;
            }
        }
        return false;
    }


}
