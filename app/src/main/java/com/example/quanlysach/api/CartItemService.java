package com.example.quanlysach.api;

import com.example.quanlysach.doiTuong.adapter.QuantityUpdate;
import com.example.quanlysach.model.Cart_item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartItemService {
    @GET("api/cart_item/")
    Call<List<Cart_item>> getAll();

    @GET("api/cart_item/{id}")
    Call<List<Cart_item>> get(@Path("id") int id);

    @GET("api/cart_item/findOne?_where=(name,eq,name)")
    Call<List<Cart_item>> get(@Path("name") String name);

    @POST("api/cart_item/")
    Call<Cart_item> create(@Body Cart_item cart_item);

    @PUT("/api/cart_item")
    Call<Cart_item> update( @Body Cart_item cart_item);



    @DELETE("/api/cart_item/{id}")
    Call<Void> delete(@Path("id") int id);


    @PATCH("api/cart_item/{id}")
    Call<Cart_item> updateQuantity(@Path("id") int id, @Body QuantityUpdate quantityUpdate);
}
