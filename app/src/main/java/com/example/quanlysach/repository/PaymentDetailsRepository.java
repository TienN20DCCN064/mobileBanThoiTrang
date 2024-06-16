package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.PaymentDetailsService;
import com.example.quanlysach.model.Payment_details;

import java.util.List;

import retrofit2.Callback;

public class PaymentDetailsRepository implements Repository<Payment_details> {
    PaymentDetailsService paymentDetailsService;

    public PaymentDetailsRepository() {
        paymentDetailsService = ApiClient.getRetrofitInstance().create(PaymentDetailsService.class);
    }

    @Override
    public void getAll(Callback<List<Payment_details>> callback) {
        paymentDetailsService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Payment_details>> callback) {
        paymentDetailsService.get(id).enqueue(callback);
    }


    @Override
    public void get(String name, Callback<List<Payment_details>> callback) {

    }

    @Override
    public void create(Payment_details obj, Callback<Payment_details> callback) {
        paymentDetailsService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Payment_details obj, Callback<Payment_details> callback) {
        paymentDetailsService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        paymentDetailsService.delete(id).enqueue(callback);
    }
}
