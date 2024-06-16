package com.example.quanlysach.model;

import java.sql.Date;

public class Shopping_Session {
    int id, account_id;
    float total;
    Date created_at, modified_at;

    public Shopping_Session() {
    }

    public Shopping_Session(int id, int account_id, float total, Date created_at, Date modified_at) {
        this.id = id;
        this.account_id = account_id;
        this.total = total;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "Shopping_Session{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", total=" + total +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
