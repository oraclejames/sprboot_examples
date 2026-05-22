package com.amazon.product.model;

import org.springframework.stereotype.Component;


public class Product {

    private int productId;
    private String productName;
    private double price;

    // 1. A default (no-argument) constructor is highly recommended for model classes
    public Product() {
    }

    // 2. Your parameterized constructor
    public Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    // 3. Standard Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}