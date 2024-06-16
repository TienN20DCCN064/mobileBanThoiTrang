package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.OrderItemsService;
import com.example.quanlysach.model.Order_items;

import java.util.List;

import retrofit2.Callback;

public class OrderItemsRepository implements Repository<Order_items> {
    OrderItemsService orderItemsService;

    public OrderItemsRepository() {
        orderItemsService = ApiClient.getRetrofitInstance().create(OrderItemsService.class);
    }

    @Override
    public void getAll(Callback<List<Order_items>> callback) {
        orderItemsService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Order_items>> callback) {
        orderItemsService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<Order_items>> callback) {

    }

    @Override
    public void create(Order_items obj, Callback<Order_items> callback) {
        orderItemsService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Order_items obj, Callback<Order_items> callback) {
        orderItemsService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        orderItemsService.delete(id).enqueue(callback);
    }
}
