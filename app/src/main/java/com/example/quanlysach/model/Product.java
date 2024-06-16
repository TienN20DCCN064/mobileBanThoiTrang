package com.example.quanlysach.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int id; // ID sản phẩm
    private String name; // Tên sản phẩm
    private String discription; // Mô tả sản phẩm
    private String category; // Loại sản phẩm
    private BigDecimal price; // Giá sản phẩm
    private int quantity; // Số lượng sản phẩm
    private ImageDataSanPham image_data;
    private int discount_id; // ID chiết khấu

    private String created_at; // Ngày tạo
    private String modified_at; // Ngày cập nhật

    // Constructor khởi tạo đối tượng Product
    public Product(int id, String name, String discription, String category, BigDecimal price, ImageDataSanPham image_data, int quantity,int discountId, String createdAt, String modifiedAt) { // Sửa tham số truyền vào từ byte[] image_data thành ImageData image_data
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.category = category;
        this.price = price;
        this.image_data = image_data; // Sửa tên biến thành image_data
        this.quantity = quantity;
        this.discount_id = discountId;
        this.created_at = createdAt;
        this.modified_at = modifiedAt;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    // Getter cho image_data
    public ImageDataSanPham getImageData() {
        return image_data;
    }

    // Setter cho image_data
    public void setImageData(ImageDataSanPham imageData) {
        this.image_data = imageData;
    }


    // Các getter và setter cho các thuộc tính
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

    public String getDescription() {
        return discription;
    }

    public void setDescription(String description) {
        this.discription = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDiscountId() {
        return discount_id;
    }

    public void setDiscountId(int discountId) {
        this.discount_id = discountId;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getModifiedAt() {
        return modified_at;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modified_at = modifiedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + discription + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity + // In ra giá trị của quantity
                ", image_data=" + image_data +
                ", discount_id=" + discount_id +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
