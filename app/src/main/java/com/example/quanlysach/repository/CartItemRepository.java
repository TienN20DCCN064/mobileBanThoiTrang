package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.CartItemService;
import com.example.quanlysach.model.Cart_item;

import java.util.List;

import retrofit2.Callback;

public class CartItemRepository implements Repository<Cart_item> {
    CartItemService cartItemService;

    public CartItemRepository() {
        cartItemService = ApiClient.getRetrofitInstance().create(CartItemService.class);
    }

    @Override
    public void getAll(Callback<List<Cart_item>> callback) {
        cartItemService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Cart_item>> callback) {
        cartItemService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<Cart_item>> callback) {
        cartItemService.get(name).enqueue(callback);
    }

    @Override
    public void create(Cart_item obj, Callback<Cart_item> callback) {
        cartItemService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Cart_item obj, Callback<Cart_item> callback) {
        cartItemService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        cartItemService.delete(id).enqueue(callback);
    }
}
