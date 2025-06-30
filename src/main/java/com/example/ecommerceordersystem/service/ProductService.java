package com.example.ecommerceordersystem.service;

import com.example.ecommerceordersystem.dto.ProductRequestDto;
import com.example.ecommerceordersystem.model.Product;
import com.example.ecommerceordersystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPriceNet(dto.getPriceNet());
        product.setVatRate(dto.getVatRate());
        product.setQuantity(dto.getQuantity());
        product.setImage(dto.getImage());
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}
