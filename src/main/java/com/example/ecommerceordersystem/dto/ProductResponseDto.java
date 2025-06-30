package com.example.ecommerceordersystem.dto;

public class ProductResponseDto {

    private Long id;
    private String name;
    private double priceNet;
    private double vatRate;
    private double priceGross;
    private String image;

    public ProductResponseDto() {}

    public ProductResponseDto(Long id, String name, double priceNet, double vatRate, String image) {
        this.id = id;
        this.name = name;
        this.priceNet = priceNet;
        this.vatRate = vatRate;
        this.priceGross = priceNet * (1 + vatRate);
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(double priceNet) {
        this.priceNet = priceNet;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public double getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(double priceGross) {
        this.priceGross = priceGross;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
