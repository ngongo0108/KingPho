package com.example.kingpho.service;

import com.example.kingpho.dto.RegisterDTO;
import com.example.kingpho.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/register")
    Call<User> registerUser(@Body RegisterDTO registerDTO);
}
