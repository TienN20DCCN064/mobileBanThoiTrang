package com.example.quanlysach.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.quanlysach.R;
import com.example.quanlysach.model.Account;
import com.example.quanlysach.repository.AccountRepository;
import com.example.quanlysach.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername, edtPass, edtRepass,edtEmail;
    AppCompatButton btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String pass= edtPass.getText().toString().trim();
                String repass = edtRepass.getText().toString().trim();
                if(username.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Chưa nhập tài khoản!", Toast.LENGTH_SHORT).show();
                }else if(email.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Chưa nhập Email!", Toast.LENGTH_SHORT).show();
                }else if(pass.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Chưa nhập Password!", Toast.LENGTH_SHORT).show();
                } else if (repass.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Chưa nhập re-password!", Toast.LENGTH_SHORT).show();
                } else if (!repass.equals(pass)) {
                    Toast.makeText(RegisterActivity.this, "re-password không chính xác, nhập lại!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Repository<Account> repository = new AccountRepository();
//                    10 is user role_id
                    repository.create(new Account(username, pass,10), new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thất bại :<", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void setControl() {
        edtUsername = findViewById(R.id.edtTK);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtRepass = findViewById(R.id.edtRepass);
        btnRegister = findViewById(R.id.btnDangky);
        tvLogin = findViewById(R.id.tvDangnhap);

    }
}