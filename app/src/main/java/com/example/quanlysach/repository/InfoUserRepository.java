package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.InfoUserService;
import com.example.quanlysach.model.InfoUser;

import java.util.List;

import retrofit2.Callback;

public class InfoUserRepository implements Repository<InfoUser> {

    InfoUserService infoUserService ;

    public InfoUserRepository() {
        infoUserService = ApiClient.getRetrofitInstance().create(InfoUserService.class);
    }

    @Override
    public void getAll(Callback<List<InfoUser>> callback) {
        infoUserService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<InfoUser>> callback) {
        infoUserService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<InfoUser>> callback) {

    }

    @Override
    public void create(InfoUser obj, Callback<InfoUser> callback) {
        infoUserService.create(obj).enqueue(callback);
    }

    @Override
    public void update(InfoUser obj, Callback<InfoUser> callback) {
        infoUserService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        infoUserService.delete(id).enqueue(callback);
    }
}
