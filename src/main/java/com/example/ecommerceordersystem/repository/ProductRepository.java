package com.example.ecommerceordersystem.repository;

import com.example.ecommerceordersystem.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
