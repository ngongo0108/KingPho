package com.example.kingpho.model;

import java.util.Date;

public class Cart {
    private int cartId;
    private User user;
    private Date createdAt;

    public Cart() {
    }

    public Cart(int cartId, User user, Date createdAt) {
        this.cartId = cartId;
        this.user = user;
        this.createdAt = createdAt;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
