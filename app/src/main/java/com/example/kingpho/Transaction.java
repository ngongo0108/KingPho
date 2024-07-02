package com.example.kingpho;

public class Transaction {
    private String date;
    private String itemName;
    private double amount;
    private String imageName;

    public Transaction(String date, String itemName, double amount, String imageName) {
        this.date = date;
        this.itemName = itemName;
        this.amount = amount;
        this.imageName = imageName;
    }

    public String getDate() {
        return date;
    }

    public String getItemName() {
        return itemName;
    }

    public double getAmount() {
        return amount;
    }

    public String getImageName() {
        return imageName;
    }
}

