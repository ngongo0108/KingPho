package com.example.kingpho;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String name;
    private double price; // Store the unit price as a double
    private int img; // Image resource ID for the food item
    private int quantity; // Quantity of the food item

    public FoodItem(String name, double price, int img, int quantity) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImg() {
        return img;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity; // Calculate total price based on quantity
    }
}
