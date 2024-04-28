package com.ks.fastfoodapi.repository;

import com.ks.fastfoodapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List <Order> findAllByCustomer_Id ( Long userId );

}