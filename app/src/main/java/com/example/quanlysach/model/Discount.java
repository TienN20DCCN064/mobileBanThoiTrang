package com.example.quanlysach.model;

import java.sql.Date;

public class Discount {
    int id;
    String name, discription;
    float discount_percent;
    Date created_at, modified_at;

    public Discount() {
    }

    public Discount(int id, String name, String discription, float discount_percent, Date created_at, Date modified_at) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.discount_percent = discount_percent;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public float getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(float discount_percent) {
        this.discount_percent = discount_percent;
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
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", discount_percent=" + discount_percent +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
