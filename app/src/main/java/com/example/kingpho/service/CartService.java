package com.example.kingpho.service;

import com.example.kingpho.model.CartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartService {

    @POST("cart")
    Call<Void> addToCart(@Body CartItem cartItem);

    @GET("cart/{userId}")
    Call<List<CartItem>> getCartItems(@Path("userId") int userId);

    @PUT("cart/{userId}")
    Call<Void> updateCartItem(@Path("userId") int userId, @Body CartItem cartItem);
}
