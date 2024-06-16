package com.example.quanlysach.model;

import java.util.Date;
public class Cart_item {
    int id, session_id, product_id;
    int quantity;
    String created_at, modified_at;
    public Cart_item(){

    };

    public Cart_item(int id, int session_id, int product_id, int quantity, String created_at, String modified_at) {
        this.id = id;
        this.session_id = session_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "Cart_item{" +
                "id=" + id +
                ", session_id=" + session_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
