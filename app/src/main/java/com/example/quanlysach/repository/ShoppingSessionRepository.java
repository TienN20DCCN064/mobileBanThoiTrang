package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.ShoppingSessionService;
import com.example.quanlysach.model.Shopping_Session;

import java.util.List;

import retrofit2.Callback;

public class ShoppingSessionRepository implements Repository<Shopping_Session> {
    ShoppingSessionService shoppingSessionService;

    public ShoppingSessionRepository() {
        shoppingSessionService = ApiClient.getRetrofitInstance().create(ShoppingSessionService.class);
    }

    @Override
    public void getAll(Callback<List<Shopping_Session>> callback) {
        shoppingSessionService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Shopping_Session>> callback) {
        shoppingSessionService.get(id).enqueue(callback);
    }


    @Override
    public void get(String name, Callback<List<Shopping_Session>> callback) {

    }

    @Override
    public void create(Shopping_Session obj, Callback<Shopping_Session> callback) {
        shoppingSessionService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Shopping_Session obj, Callback<Shopping_Session> callback) {
        shoppingSessionService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        shoppingSessionService.delete(id).enqueue(callback);
    }
}
