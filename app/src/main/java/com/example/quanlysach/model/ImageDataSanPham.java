package com.example.quanlysach.model;

import java.util.List;

public class ImageDataSanPham {

        private String type;
        private List<Integer> data;

        // Constructor
        public ImageDataSanPham(String type, List<Integer> data) {
            this.type = type;
            this.data = data;
        }

        // Getter và setter cho type
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        // Getter và setter cho data
        public List<Integer> getData() {
            return data;
        }

        public void setData(List<Integer> data) {
            this.data = data;
        }
}
