package com.example.ecommerceordersystem.repository;

import com.example.ecommerceordersystem.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
