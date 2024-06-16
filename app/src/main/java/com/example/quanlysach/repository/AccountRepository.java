package com.example.quanlysach.repository;

import com.example.quanlysach.api.AccountService;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.model.Account;

import java.util.List;

import retrofit2.Callback;

public class AccountRepository implements Repository<Account>{
    AccountService accountService;

    public AccountRepository() {
        accountService = ApiClient.getRetrofitInstance().create(AccountService.class);
    }

    @Override
    public void getAll(Callback<List<Account>> callback) {
        accountService.getAll().enqueue(callback);
    }

    @Override
    public void get(int id, Callback<List<Account>> callback) {
        accountService.get(id).enqueue(callback);

    }

    @Override
    //username syntax = (col, eq , value )
    public void get(String username, Callback<List<Account>> callback) {
        String syntax = "(username,eq," + username+")";
        accountService.get(syntax).enqueue(callback);

    }

    @Override
    public void create(Account obj, Callback<Account> callback) {
        accountService.create(obj).enqueue(callback);
    }

    @Override
    public void update(Account obj, Callback<Account> callback) {
        accountService.update(obj).enqueue(callback);
    }

    @Override
    public void delete(int id, Callback<Void> callback) {
        accountService.delete(id).enqueue(callback);
    }
}
