package com.example.quanlysach.api;

import com.example.quanlysach.model.Order_details;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderDetailsService {
    @GET("api/order_details/")
    Call<List<Order_details>> getAll();

    @GET("api/order_details/{id}")
    Call<List<Order_details>> get(@Path("id") int id);

    @POST("api/order_details/")
    Call<Order_details> create(@Body Order_details order_details);

    @PUT("/api/order_details")
    Call<Order_details> update( @Body Order_details order_details);

    @DELETE("/api/order_details/{id}")
    Call<Void> delete(@Path("id") int id);
}
