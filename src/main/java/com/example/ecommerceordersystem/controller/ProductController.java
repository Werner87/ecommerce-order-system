package com.example.ecommerceordersystem.controller;

import com.example.ecommerceordersystem.dto.ProductRequestDto;
import com.example.ecommerceordersystem.dto.ProductResponseDto;
import com.example.ecommerceordersystem.model.Product;
import com.example.ecommerceordersystem.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto) {
        Product product = productService.createProduct(dto);
        ProductResponseDto response = new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPriceNet(),
                product.getVatRate(),
                product.getImage()
        );
        return ResponseEntity.ok(response);
    }
}
