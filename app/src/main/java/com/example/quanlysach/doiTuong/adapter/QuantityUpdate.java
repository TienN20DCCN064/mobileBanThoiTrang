package com.example.quanlysach.doiTuong.adapter;


public class QuantityUpdate {
    private int quantity;

    public QuantityUpdate(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "QuantityUpdate{" +
                "quantity=" + quantity +
                '}';
    }
}
