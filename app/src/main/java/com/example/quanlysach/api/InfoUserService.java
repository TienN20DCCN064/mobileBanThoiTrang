package com.example.quanlysach.api;

import com.example.quanlysach.model.InfoUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InfoUserService {
    @GET("api/infouser/")
    Call<List<InfoUser>> getAll();

    @GET("api/infouser/{id}")
    Call<List<InfoUser>> get(@Path("id") int id);

    @POST("api/infouser/")
    Call<InfoUser> create(@Body InfoUser infouser);

    @PUT("/api/infouser")
    Call<InfoUser> update( @Body InfoUser infouser);

    @DELETE("/api/infouser/{id}")
    Call<Void> delete(@Path("id") int id);
}
