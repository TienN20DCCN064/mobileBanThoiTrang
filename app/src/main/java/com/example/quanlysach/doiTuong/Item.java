package com.example.quanlysach.doiTuong;

// Item.java
public class Item {
    private int imageResource;
    private String tenSanPham;
    private String loaiSanPham;
    private String mieutaSanPham;
    private String giaBanSanPham;

    public Item(int imageResource, String tenSanPham, String loaiSanPham, String mieutaSanPham,String giaBanSanPham) {
        this.imageResource = imageResource;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.giaBanSanPham = giaBanSanPham;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public String getMieutaSanPham() {
        return mieutaSanPham;
    }

    public String getGiaBanSanPham() {
        return giaBanSanPham;
    }
}
