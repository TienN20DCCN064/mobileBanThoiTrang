package com.example.quanlysach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.utils.MenuUtils;

public class ThanhToanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int tongTien = intent.getIntExtra("tongTien", 0);

        // Hiển thị dữ liệu (ví dụ: trong TextView)
        TextView textViewTongTien = findViewById(R.id.textViewTongTien);
        textViewTongTien.setText("Tổng tiền: " + tongTien + " $");

        MenuUtils.setMenuButton(this);
    }
}
