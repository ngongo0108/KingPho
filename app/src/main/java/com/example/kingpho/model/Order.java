package com.example.kingpho.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private List<FoodItem> foodItems;
    private String status;
    private String orderNumber;
    private double totalPrice;
    private int img; // Image resource ID for the order
    private String estimatedTime;

    public Order(List<FoodItem> foodItems, String status, String orderNumber, double totalPrice, int img, String estimatedTime) {
        this.foodItems = foodItems;
        this.status = status;
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.img = img;
        this.estimatedTime = estimatedTime;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getImg() {
        return img;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }
}
