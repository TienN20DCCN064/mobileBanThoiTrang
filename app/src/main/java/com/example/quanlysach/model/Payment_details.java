package com.example.quanlysach.model;

import java.sql.Date;

public class Payment_details {
    int id, order_id, amount;
    String provider, status;
    Date created_at, modified_at;

    public Payment_details() {
    }

    public Payment_details(int id, int order_id, int amount, String provider, String status, Date created_at, Date modified_at) {
        this.id = id;
        this.order_id = order_id;
        this.amount = amount;
        this.provider = provider;
        this.status = status;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Payment_details{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", amount=" + amount +
                ", provider='" + provider + '\'' +
                ", status='" + status + '\'' +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
