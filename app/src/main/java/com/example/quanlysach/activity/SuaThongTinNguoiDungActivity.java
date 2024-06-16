package com.example.quanlysach.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class SuaThongTinNguoiDungActivity extends AppCompatActivity {

    private EditText edtFullName, edtGmail, edtPhone;
    private AppCompatButton btSuaThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin_nguoi_dung);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String userIdStr = sharedPreferences.getString("USER_ID", null);

        // Tham chiếu đến các thành phần giao diện
        edtFullName = findViewById(R.id.edtfullname);
        edtGmail = findViewById(R.id.edtGmail);
        edtPhone = findViewById(R.id.edtPhone);
        btSuaThongTin = findViewById(R.id.btSuaThonTin);

        btSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ các EditText
                String fullName = edtFullName.getText().toString().trim();
                String gmail = edtGmail.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();

                // Kiểm tra các giá trị đầu vào
                if (fullName.isEmpty() || gmail.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (fullName.length() <= 5) {
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Họ tên phải dài hơn 5 ký tự", Toast.LENGTH_SHORT).show();
                } else if (!gmail.contains("@")) {
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Gmail không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (!phone.startsWith("0")) {
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Số điện thoại phải bắt đầu bằng số 0", Toast.LENGTH_SHORT).show();
                } else if (!phone.matches("\\d+")) {  // Kiểm tra chỉ chứa các ký tự số
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Số điện thoại chỉ được chứa các chữ số", Toast.LENGTH_SHORT).show();
                } else if (phone.length() != 10) {  // Kiểm tra độ dài số điện thoại
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Số điện thoại phải có đúng 10 chữ số", Toast.LENGTH_SHORT).show();
                } else {
                    getUser(Integer.parseInt(userIdStr));
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        MenuUtils.setMenuButton(this);
    }



    private void getUser(int userId) {
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

                            InfoUser infoUserToUpdate = new InfoUser(); // Thay bằng thông tin người dùng cần cập nhật
                            infoUserToUpdate = user;
                            Log.e("infoUserToUpdate1",infoUserToUpdate.toString());
                            String fullName = edtFullName.getText().toString().trim();
                            String gmail = edtGmail.getText().toString().trim();
                            String phone = edtPhone.getText().toString().trim();

                            infoUserToUpdate.setFullname(fullName);
                            infoUserToUpdate.setNumbPhone(phone);
                            infoUserToUpdate.setEmail(gmail);

                            Log.e("infoUserToUpdate2",infoUserToUpdate.toString());
                            updateUserInfo(infoUserToUpdate);
                            break;
                        }
                    }

                    if (!userFound) {
                        Toast.makeText(SuaThongTinNguoiDungActivity.this, "Không tìm thấy người dùng với ID: " + userId, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Lỗi khi lấy dữ liệu từ server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<InfoUser>> call, Throwable t) {
                Toast.makeText(SuaThongTinNguoiDungActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateUserInfo(InfoUser infoUserToUpdate) {
        // Tạo đối tượng Retrofit
        InfoUserService infoUserService = ApiClient.getRetrofitInstance().create(InfoUserService.class);

        // Gọi API cập nhật thông tin người dùng
        Call<InfoUser> call = infoUserService.update(infoUserToUpdate);

        // Xử lý kết quả của lời gọi API
        call.enqueue(new Callback<InfoUser>() {
            @Override
            public void onResponse(Call<InfoUser> call, Response<InfoUser> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(SuaThongTinNguoiDungActivity.this, ThongTinNguoiDungActivity.class); // Thay NewActivity bằng tên activity bạn muốn chuyển đến
                    startActivity(intent);
                    finish();    } else {
                    // Xử lý khi cập nhật không thành công
                    Toast.makeText(SuaThongTinNguoiDungActivity.this, "Cập nhật thông tin không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InfoUser> call, Throwable t) {
                // Xử lý khi gặp lỗi trong quá trình gọi API
                Toast.makeText(SuaThongTinNguoiDungActivity.this, "Đã xảy ra lỗi khi cập nhật thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
