package com.example.quanlysach.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.model.Account;
import com.example.quanlysach.model.InfoUser;
import com.example.quanlysach.model.Product;
import com.example.quanlysach.model.Role;
import com.example.quanlysach.repository.AccountRepository;
import com.example.quanlysach.repository.InfoUserRepository;
import com.example.quanlysach.repository.ProductRepository;
import com.example.quanlysach.repository.Repository;
import com.example.quanlysach.repository.RoleRepository;

public class TestActivity extends AppCompatActivity {
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tv = findViewById(R.id.tv);
//        getRole(11);
//        getAllRole();
//        createRole("super Admin");
//        updateRole( new Role(19, "super puw"));
//        deleteRole(18);



//        Test Role CRUD
        Repository<Role> roleRepository = new RoleRepository();
//        roleRepository.getAll(new Callback<List<Role>>() {
//            @Override
//            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
//                String s = "";
//                for(Role item:response.body())
//                {
//                    s += item.toString();
//                }
//                tv.setText(s);
//            }
//
//            @Override
//            public void onFailure(Call<List<Role>> call, Throwable t) {
//                Toast.makeText(TestActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//            }
//        });
//        roleRepository.get(11, new Callback<List<Role>>() {
//            @Override
//            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
//                String s = response.body().toString()   ;
//                tv.setText(s);
//            }
//
//            @Override
//            public void onFailure(Call<List<Role>> call, Throwable t) {
//
//            }
//        });
//        roleRepository.create(new Role("iuemnhieulem"), new Callback<Role>() {
//            @Override
//            public void onResponse(Call<Role> call, Response<Role> response) {
//                tv.setText(" Thêm thành công !");
//            }
//
//            @Override
//            public void onFailure(Call<Role> call, Throwable t) {
//                tv.setText(" Thêm thất bại !");
//
//            }
//        });
//        roleRepository.update(new Role(23, "anhbungbu"), new Callback<Role>() {
//            @Override
//            public void onResponse(Call<Role> call, Response<Role> response) {
//                tv.setText(" Update thành công !");
//            }
//
//            @Override
//            public void onFailure(Call<Role> call, Throwable t) {
//                tv.setText(" Update thất bại !");
//            }
//        });
//        roleRepository.delete(15, new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                tv.setText("Xoá thành công!");
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                tv.setText("Xoá thất bại!");
//            }
//        });



//        Test Account CRUD
        Repository<Account> accountRepository = new AccountRepository();
//        accountRepository.create(new Account("pu", "pu", 10), new Callback<Account>() {
//            @Override
//            public void onResponse(Call<Account> call, Response<Account> response) {
//                tv.setText("Thêm tài khoản thành công!");
//            }
//
//            @Override
//            public void onFailure(Call<Account> call, Throwable t) {
//                tv.setText("Thêm tài khoản thất bại!");
//            }
//        });

//        accountRepository.get(1, new Callback<List<Account>>() {
//            @Override
//            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
//                String s = response.body().toString();
//                tv.setText(s);
//            }
//
//            @Override
//            public void onFailure(Call<List<Account>> call, Throwable t) {
//                tv.setText("Get tài khoản thất bại");
//            }
//        });

//        accountRepository.getAll(new Callback<List<Account>>() {
//            @Override
//            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
//                String s = "";
//                for(Account item:response.body())
//                {
//                    s += item.toString();
//                }
//                tv.setText(s);
//            }
//
//            @Override
//            public void onFailure(Call<List<Account>> call, Throwable t) {
//
//            }
//        });

//        accountRepository.update(new Account("hungdz", "hungdz", 11), new Callback<Account>() {
//            @Override
//            public void onResponse(Call<Account> call, Response<Account> response) {
//                tv.setText("Update tài khoản thành công!");
//            }
//
//            @Override
//            public void onFailure(Call<Account> call, Throwable t) {
//                tv.setText("Update tài khoản thất bại!");
//            }
//        });

//        accountRepository.delete(3, new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                tv.setText("Xoá tài khoản thành công!");
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                tv.setText("Xoá tài khoản thất bại!");
//            }
//        });


        //Test InfoUser CRUD
        Repository<InfoUser> infoUserRepository = new InfoUserRepository();

//        infoUserRepository.create(new InfoUser("Huỳnh Gia Hưng", "0335535473", "giahung@gmail.com", 1),
//                new Callback<InfoUser>() {
//                    @Override
//                    public void onResponse(Call<InfoUser> call, Response<InfoUser> response) {
//                        tv.setText("Tạo thông tin tài khoản thành công!");
//                    }
//
//                    @Override
//                    public void onFailure(Call<InfoUser> call, Throwable t) {
//                        tv.setText("Tạo thông tin tài khoản thất bại!");
//                    }
//                });

//        infoUserRepository.get(1, new Callback<List<InfoUser>>() {
//            @Override
//            public void onResponse(Call<List<InfoUser>> call, Response<List<InfoUser>> response) {
//                tv.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<InfoUser>> call, Throwable t) {
//                tv.setText("Get thông tin user thất bại!");
//            }
//        });


//        infoUserRepository.update(new InfoUser("Pư siu mẫu", "098765432", "pu@gmail.com", 2),
//                new Callback<InfoUser>() {
//                    @Override
//                    public void onResponse(Call<InfoUser> call, Response<InfoUser> response) {
//                        tv.setText("Update thông tin thành công !");
//                    }
//
//                    @Override
//                    public void onFailure(Call<InfoUser> call, Throwable t) {
//                        tv.setText("Update thông tin thất bại !");
//                    }
//                });
//        infoUserRepository.getAll(new Callback<List<InfoUser>>() {
//            @Override
//            public void onResponse(Call<List<InfoUser>> call, Response<List<InfoUser>> response) {
//                String s = "";
//                for(InfoUser item:response.body())
//                {
//                    s += item.toString()    ;
//                }
//                tv.setText(s);
//            }
//
//            @Override
//            public void onFailure(Call<List<InfoUser>> call, Throwable t) {
//                tv.setText("GetALL thất baại!!!");
//            }
//        });



//        Test Product CRUD
        Repository<Product> productRepository = new ProductRepository();

        

    }
}