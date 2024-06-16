package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.OrderDetailsService;
import com.example.quanlysach.model.Order_details;

import java.util.List;

import retrofit2.Callback;

public class OrderDetailsRepository implements Repository<Order_details> {
    OrderDetailsService orderDetailsService;

    public OrderDetailsRepository() {
        orderDetailsService  = ApiClient.getRetrofitInstance().create(OrderDetailsService.class);
    }

    @Override
    public void getAll(Callback<List<Order_details>> callback) {
        orderDetailsService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Order_details>> callback) {
        orderDetailsService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<Order_details>> callback) {

    }

    @Override
    public void create(Order_details obj, Callback<Order_details> callback) {
        orderDetailsService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Order_details obj, Callback<Order_details> callback) {
        orderDetailsService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        orderDetailsService.delete(id).enqueue(callback);
    }
}
