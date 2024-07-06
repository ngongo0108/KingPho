package com.example.kingpho.model;

public class Category {
    private String categoryTitle;
    private String categoryImage;

    public Category(String categoryTitle, String categoryImage) {
        this.categoryTitle = categoryTitle;
        this.categoryImage = categoryImage;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
}
