package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.ProductService;
import com.example.quanlysach.model.Product;

import java.util.List;

import retrofit2.Callback;

public class ProductRepository implements Repository<Product>{
    ProductService productService;

    public ProductRepository() {
        productService = ApiClient.getRetrofitInstance().create(ProductService.class);
    }

    @Override
    public void getAll(Callback<List<Product>> callback) {
        productService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Product>> callback) {
        productService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<Product>> callback) {

    }

    @Override
    public void create(Product obj, Callback<Product> callback) {
        productService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Product obj, Callback<Product> callback) {
        productService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        productService.delete(id).enqueue(callback);
    }
}
