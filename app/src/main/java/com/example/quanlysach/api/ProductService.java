package com.example.quanlysach.api;

import com.example.quanlysach.doiTuong.adapter.QuantityUpdate;
import com.example.quanlysach.model.Cart_item;
import com.example.quanlysach.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @GET("api/product/")
    Call<List<Product>> getAll();

    @GET("api/product/{id}")
    Call<List<Product>> get(@Path("id") int id);

    @POST("api/product/")
    Call<Product> create(@Body Product product);

    @PUT("/api/product")
    Call<Product> update( @Body Product product);

    @DELETE("/api/product/{id}")
    Call<Void> delete(@Path("id") int id);
    @PATCH("api/product/{id}")
    Call<Cart_item> updateQuantity(@Path("id") int id, @Body QuantityUpdate quantityUpdate);
}
