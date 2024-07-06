//package com.example.kingpho.model;
//
//public class Food {
//    private String title;
//    private String pic;
//    private String description;
//    private Double price;
//    private int numberInCart;
//
//    public Food(String title, String pic, String description, Double price) {
//        this.title = title;
//        this.pic = pic;
//        this.description = description;
//        this.price = price;
//    }
//
//    public Food(String title, String pic, String description, Double price, int numberInCart) {
//        this.title = title;
//        this.pic = pic;
//        this.description = description;
//        this.price = price;
//        this.numberInCart = numberInCart;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getPic() {
//        return pic;
//    }
//
//    public void setPic(String pic) {
//        this.pic = pic;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public int getNumberInCart() {
//        return numberInCart;
//    }
//
//    public void setNumberInCart(int numberInCart) {
//        this.numberInCart = numberInCart;
//    }
//}
package com.example.kingpho.model;

public class Food {
    private String foodTitle;
    private String foodImage;
    private String foodDescription;
    private Double foodPrice;
    private int numberInCart;
    private Category category;

    public Food(String foodTitle, String foodImage, String foodDescription, Double foodPrice, Category category) {
        this.foodTitle = foodTitle;
        this.foodImage = foodImage;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.category = category;
    }

    public Food(String foodTitle, String foodImage, String foodDescription, Double foodPrice, int numberInCart, Category category) {
        this.foodTitle = foodTitle;
        this.foodImage = foodImage;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.numberInCart = numberInCart;
        this.category = category;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
