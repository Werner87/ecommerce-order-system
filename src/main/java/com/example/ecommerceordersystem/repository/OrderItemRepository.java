package com.example.ecommerceordersystem.repository;

import com.example.ecommerceordersystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
