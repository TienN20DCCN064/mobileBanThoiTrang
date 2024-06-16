package com.example.quanlysach.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.quanlysach.R;
import com.example.quanlysach.activity.TrangChuActivity;
import com.example.quanlysach.model.Account;
import com.example.quanlysach.repository.AccountRepository;
import com.example.quanlysach.repository.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername,edtPassword;
    TextView tvForgetPassword, tvRegister;
    CheckBox chkLuuMK;
    AppCompatButton btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if(username.equals("") || password.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Chưa điền thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                }else
                {
                    Repository<Account> account = new AccountRepository();
                    account.get(username, new Callback<List<Account>>() {
                        @Override
                        public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                            if(!response.body().get(0).getUsername().equals(username) )
                            {
                                Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_LONG).show();
                            }else if (!response.body().get(0).getPassword().equals(password))
                            {
                                Toast.makeText(LoginActivity.this, "Sai mật khẩu", Toast.LENGTH_LONG).show();
                            }else
                            {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

                                String userId = response.body().get(0).getId()+"";
                                String userName = response.body().get(0).getUsername();
                                // Lưu ID vào SharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("USERNAME", userName);
                                editor.putString("USER_ID", userId);
                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, TrangChuActivity.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Account>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "errre", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvForgetPassword = findViewById(R.id.tvQuenMK);
        chkLuuMK = findViewById(R.id.chkLuuMK);
        btnLogin = findViewById(R.id.btDangNhap);
        tvRegister = findViewById(R.id.tvDangKy);
        
    }
}