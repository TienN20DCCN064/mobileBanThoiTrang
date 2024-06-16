package com.example.quanlysach.api;

import com.example.quanlysach.model.Role;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RoleService{
    @GET("api/role/")
    Call<List<Role>> getAll();

    @GET("api/role/{id}")
    Call<List<Role>> get(@Path("id") int id);

    @POST("api/role/")
    Call<Role> create(@Body Role role);

    @PUT("/api/role")
    Call<Role> update( @Body Role role);

    @DELETE("/api/role/{id}")
    Call<Void> delete(@Path("id") int id);
}

