package com.example.quanlysach.model;

import java.sql.Date;

public class Order_details {
    int id, account_id, payment_id;
    float total;
    Date created_at, modified_at;

    public Order_details() {
    }

    public Order_details(int id, int account_id, int payment_id, float total, Date created_at, Date modified_at) {
        this.id = id;
        this.account_id = account_id;
        this.payment_id = payment_id;
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

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
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
        return "Order_details{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", payment_id=" + payment_id +
                ", total=" + total +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
