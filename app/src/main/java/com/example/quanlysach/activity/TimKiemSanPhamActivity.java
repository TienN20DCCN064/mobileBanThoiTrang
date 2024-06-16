package com.example.quanlysach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.utils.MenuUtils;

public class TimKiemSanPhamActivity extends AppCompatActivity {
    private Button btnTimKiemSach;
    private EditText idtTimKiemSanPham;
    // t

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_san_pham);

        // Ánh xạ nút "Tìm kiếm"
        btnTimKiemSach = findViewById(R.id.butTonTimKiemSach);
        idtTimKiemSanPham = findViewById(R.id.idtTimKiemSanPham);

        // Gắn bộ lắng nghe sự kiện cho nút "Tìm kiếm"
        btnTimKiemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(idtTimKiemSanPham!=null){
                  String searchText = idtTimKiemSanPham.getText().toString();
                  // Tạo Intent để chuyển đến TimKiemSanPhamListActivity
                  Intent intent = new Intent(TimKiemSanPhamActivity.this, TimKiemSanPhamListActivity.class);
                  // Đính kèm giá trị từ EditText vào Intent
                  intent.putExtra("SEARCH_TEXT", searchText);
                  // Chuyển đến TimKiemSanPhamListActivity
                  startActivity(intent);
              }
              else {
                  Toast.makeText(TimKiemSanPhamActivity.this, "Vui lòng nhập tên sách", Toast.LENGTH_SHORT).show();
              }
            }
        });


        MenuUtils.setMenuButton(this);
    }
}