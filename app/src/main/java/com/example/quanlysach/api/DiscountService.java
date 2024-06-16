package com.example.quanlysach.api;

import com.example.quanlysach.model.Discount;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DiscountService{
    @GET("api/discount/")
    Call<List<Discount>> getAll();

    @GET("api/discount/{id}")
    Call<List<Discount>> get(@Path("id") int id);

    @POST("api/discount/")
    Call<Discount> create(@Body Discount discount);

    @PUT("/api/discount")
    Call<Discount> update( @Body Discount discount);

    @DELETE("/api/discount/{id}")
    Call<Void> delete(@Path("id") int id);
}
