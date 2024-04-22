package com.ks.fastfoodapi.repository;

import com.ks.fastfoodapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}