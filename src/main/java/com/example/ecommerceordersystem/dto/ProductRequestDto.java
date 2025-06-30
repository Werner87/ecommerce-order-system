package com.example.ecommerceordersystem.dto;

import jakarta.validation.constraints.*;

public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Net price is required")
    @PositiveOrZero(message = "Net price must be zero or positive")
    private Double priceNet;

    @NotNull(message = "VAT rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "VAT rate must be non-negative")
    @DecimalMax(value = "1.0", inclusive = true, message = "VAT rate must not exceed 1.0 (100%)")
    private Double vatRate;

    @Min(value = 0, message = "Quantity must be at least 0")
    private int quantity;

    @NotBlank(message = "Image URL is required")
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(Double priceNet) {
        this.priceNet = priceNet;
    }

    public Double getVatRate() {
        return vatRate;
    }

    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
