package com.example.quanlysach.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.quanlysach.R;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.InfoUserService;
import com.example.quanlysach.model.InfoUser;
import com.example.quanlysach.utils.MenuUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinNguoiDungActivity extends AppCompatActivity {
    private TextView tvTenTk1;
    private TextView tvEmail1;
    private TextView tvfullName;
    private TextView tvEmail;
    private TextView tvnNumberPhone;
    private AppCompatButton btSuaThongTin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nguoi_dung);
        // Khởi tạo các TextView
        tvTenTk1 = findViewById(R.id.tvTenTk1);
        tvEmail1 = findViewById(R.id.tvEmail1);
        tvfullName = findViewById(R.id.tvfullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvnNumberPhone = findViewById(R.id.tvnNumberPhone);
        btSuaThongTin = findViewById(R.id.btSuaThonTin);

        // Lấy giá trị ID từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String userIdStr = sharedPreferences.getString("USER_ID", null);
        String userNameStr = sharedPreferences.getString("USERNAME", null);

        MenuUtils.setMenuButton(this);
        if (userIdStr != null || userNameStr !=null) {
            int userId = Integer.parseInt(userIdStr);
            tvTenTk1.setText(userNameStr);
            getAllUsers(userId);

            btSuaThongTin.setOnClickListener(v -> {
                Intent intent = new Intent(ThongTinNguoiDungActivity.this, SuaThongTinNguoiDungActivity.class);
                startActivity(intent);
            });
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAllUsers(int userId) {
        InfoUserService service = ApiClient.getRetrofitInstance().create(InfoUserService.class);
        Call<List<InfoUser>> call = service.getAll();
        call.enqueue(new Callback<List<InfoUser>>() {
            @Override
            public void onResponse(Call<List<InfoUser>> call, Response<List<InfoUser>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InfoUser> infoUsers = response.body();

                    // Kiểm tra từng đối tượng trong danh sách
                    boolean userFound = false;
                    for (InfoUser user : infoUsers) {
                        if (user.getAccount_id() == userId) {
                            userFound = true;
                            updateUI(user);
                            break;
                        }
                    }

                    if (!userFound) {
                        Toast.makeText(ThongTinNguoiDungActivity.this, "Không tìm thấy người dùng với ID: " + userId, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThongTinNguoiDungActivity.this, "Lỗi khi lấy dữ liệu từ server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<InfoUser>> call, Throwable t) {
                Toast.makeText(ThongTinNguoiDungActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateUI(InfoUser user) {

        tvEmail1.setText(user.getEmail());
        tvfullName.setText("Họ Và Tên : "+user.getFullname());
        tvEmail.setText("Gmail: " + user.getEmail());
        tvnNumberPhone.setText("Số điện thoại: " + user.getNumbPhone());
    }


}
