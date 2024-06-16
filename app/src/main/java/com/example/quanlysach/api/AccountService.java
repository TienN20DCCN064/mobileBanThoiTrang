package com.example.quanlysach.api;

import com.example.quanlysach.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.OPTIONS;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

public interface AccountService {
    @GET("api/account/")
    Call<List<Account>> getAll();

    @GET("api/account/{id}")
    Call<List<Account>> get(@Path("id") int id);

    @GET("api/account/findOne")

    Call<List<Account>> get(@Query("_where") String name);

    @POST("api/account/")
    Call<Account> create(@Body Account account);

    @PUT("/api/account")
    Call<Account> update( @Body Account account);

    @DELETE("/api/account/{id}")
    Call<Void> delete(@Path("id") int id);
}
