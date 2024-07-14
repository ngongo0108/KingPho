package com.example.kingpho.service;

import com.example.kingpho.dto.UserFavouriteDTO;
import com.example.kingpho.model.UserFavourite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserFavouriteService {

    @POST("favourites")
    Call<UserFavourite> addToFavourites(@Body UserFavouriteDTO userFavouriteDTO);

    @GET("/api/favorites/{userId}")
    Call<List<UserFavourite>> getUserFavourites(@Path("userId") Integer userId);

    @GET("/api/favorites/{userId}/{productId}")
    Call<UserFavourite> getUserFavourite(@Path("userId") Integer userId, @Path("productId") Integer productId);

    @DELETE("/api/favorites/{id}")
    Call<Void> deleteUserFavourite(@Path("id") Integer id);
}
