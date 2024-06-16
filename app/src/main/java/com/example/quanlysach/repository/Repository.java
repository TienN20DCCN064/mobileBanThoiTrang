package com.example.quanlysach.repository;

import java.util.List;

import retrofit2.Callback;

public interface Repository<T> {

    public void getAll(Callback<List<T>> callback);
    public void get(int id, Callback<List<T>> callback );
    public void get(String name, Callback<List<T>> callback );

    public void create(T obj,Callback<T> callback);
    public void update(T obj,Callback<T> callback);
    public void delete(int id, Callback<Void> callback);
}
