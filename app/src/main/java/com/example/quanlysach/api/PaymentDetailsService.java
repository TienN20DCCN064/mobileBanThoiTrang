package com.example.quanlysach.api;

import com.example.quanlysach.model.Payment_details;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PaymentDetailsService {
    @GET("api/payment_details/")
    Call<List<Payment_details>> getAll();

    @GET("api/payment_details/{id}")
    Call<List<Payment_details>> get(@Path("id") int id);

    @POST("api/payment_details/")
    Call<Payment_details> create(@Body Payment_details payment_details);

    @PUT("/api/payment_details")
    Call<Payment_details> update( @Body Payment_details payment_details);

    @DELETE("/api/payment_details/{id}")
    Call<Void> delete(@Path("id") int id);
}
