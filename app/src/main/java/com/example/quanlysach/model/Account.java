package com.example.quanlysach.model;

import com.google.type.DateTime;

import java.sql.Date;

public class Account {
    int id;
    String username, password;
    String updateDay;
    int role_id;

    public Account() {
    }

    public Account(int id, String username, String password, String updateDay, int role_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.updateDay = updateDay;
        this.role_id = role_id;
    }

    public Account(String username, String password, int role_id) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdateDay() {
        return updateDay;
    }

    public void setUpdateDay(String updateDay) {
        this.updateDay = updateDay;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", updateDay=" + updateDay +
                ", role_id=" + role_id +
                '}';
    }
}
