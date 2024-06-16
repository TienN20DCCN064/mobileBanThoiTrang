package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.DiscountService;
import com.example.quanlysach.model.Discount;

import java.util.List;

import retrofit2.Callback;

public class DiscountRepository implements Repository<Discount> {

    DiscountService discountService;

    public DiscountRepository() {
        discountService = ApiClient.getRetrofitInstance().create(DiscountService.class);
    }

    @Override
    public void getAll(Callback<List<Discount>> callback) {
        discountService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Discount>> callback) {
        discountService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<Discount>> callback) {

    }

    @Override
    public void create(Discount obj, Callback<Discount> callback) {
        discountService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Discount obj, Callback<Discount> callback) {
        discountService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        discountService.delete(id).enqueue(callback);
    }
}
