package com.example.quanlysach.repository;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.RoleService;
import com.example.quanlysach.model.Role;

import java.util.List;

import retrofit2.Callback;

public class RoleRepository implements Repository<Role> {
    RoleService roleService;
    public RoleRepository() {
        roleService = ApiClient.getRetrofitInstance().create(RoleService.class);
    }

    @Override
    public void getAll(Callback<List<Role>> callback) {
        roleService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Role>> callback  ) {
        roleService.get(id).enqueue(callback);
    }

    @Override
    public void get(String name, Callback<List<Role>> callback) {

    }

    @Override
    public void create(Role obj, Callback<Role> callback) {
        roleService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Role obj, Callback<Role> callback) {
        roleService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        roleService.delete(id).enqueue(callback);
    }
}
