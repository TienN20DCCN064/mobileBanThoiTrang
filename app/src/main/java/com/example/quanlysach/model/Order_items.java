package com.example.quanlysach.model;

import java.sql.Date;

public class Order_items {
    int id, order_id, product_id;
    Date created_at, modified_at;

    public Order_items() {
    }

    public Order_items(int id, int order_id, int product_id, Date created_at, Date modified_at) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
        return "Order_items{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
