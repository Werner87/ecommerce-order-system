package com.example.ecommerceordersystem.repository;

import com.example.ecommerceordersystem.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
