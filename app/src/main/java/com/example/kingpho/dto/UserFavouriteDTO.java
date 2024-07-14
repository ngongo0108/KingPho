package com.example.kingpho.dto;

import com.google.gson.annotations.SerializedName;

public class UserFavouriteDTO {

    @SerializedName("id")
    private Integer id;

    @SerializedName("userId")
    private Integer userId;

    @SerializedName("productId")
    private Integer productId;
    @SerializedName("isFavourite")
    private Boolean isFavourite;

    // Constructor
    public UserFavouriteDTO(Integer userId, Integer productId, Boolean isFavourite) {
        this.userId = userId;
        this.productId = productId;
        this.isFavourite = isFavourite;
    }

    // Getters and setters (can be generated automatically)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}