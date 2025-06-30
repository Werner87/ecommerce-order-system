package com.example.ecommerceordersystem.controller;

import com.example.ecommerceordersystem.dto.CustomerRequestDto;
import com.example.ecommerceordersystem.dto.CustomerResponseDto;
import com.example.ecommerceordersystem.model.Customer;
import com.example.ecommerceordersystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto dto) {
        Customer customer = customerService.createCustomer(dto);
        CustomerResponseDto response = new CustomerResponseDto();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setSurname(customer.getSurname());
        response.setCity(customer.getAddress().getCity());
        return ResponseEntity.ok(response);
    }
}
