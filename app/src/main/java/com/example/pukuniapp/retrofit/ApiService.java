package com.example.pukuniapp.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/users")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @GET("/verify")
    Call<Void> verifyToken(@Header("Authorization") String token);
}
