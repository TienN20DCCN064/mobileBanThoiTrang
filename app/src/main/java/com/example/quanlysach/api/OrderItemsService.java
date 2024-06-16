package com.example.quanlysach.api;

import com.example.quanlysach.model.Order_items;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderItemsService {
    @GET("api/order_items/")
    Call<List<Order_items>> getAll();

    @GET("api/order_items/{id}")
    Call<List<Order_items>> get(@Path("id") int id);

    @POST("api/order_items/")
    Call<Order_items> create(@Body Order_items order_items);

    @PUT("/api/order_items")
    Call<Order_items> update( @Body Order_items order_items);

    @DELETE("/api/order_items/{id}")
    Call<Void> delete(@Path("id") int id);
}
