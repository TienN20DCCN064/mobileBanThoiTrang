package com.example.quanlysach.api;

import com.example.quanlysach.model.Shopping_Session;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShoppingSessionService {
    @GET("api/shopping_session/")
    Call<List<Shopping_Session>> getAll();

    @GET("api/shopping_session/{id}")
    Call<List<Shopping_Session>> get(@Path("id") int id);

    @POST("api/shopping_session/")
    Call<Shopping_Session> create(@Body Shopping_Session shopping_session);

    @PUT("/api/shopping_session")
    Call<Shopping_Session> update( @Body Shopping_Session shopping_session);

    @DELETE("/api/shopping_session/{id}")
    Call<Void> delete(@Path("id") int id);
}
