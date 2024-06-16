package com.example.quanlysach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.CartItemService;
import com.example.quanlysach.api.ProductService;
import com.example.quanlysach.doiTuong.adapter.CustomListGioHangAdapter;
import com.example.quanlysach.doiTuong.adapter.QuantityUpdate;
import com.example.quanlysach.model.Cart_item;
import com.example.quanlysach.model.Product;
import com.example.quanlysach.utils.MenuUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GioHangActivity extends AppCompatActivity {
    private int tongTien = 0;
    private ListView listViewGioHang;
    private Button butTonXoaSanPham,butTonMuaSanPhan;
    private CustomListGioHangAdapter adapter;
    private ArrayList<Cart_item> gioHangItems;
    private CartItemService cartItemService; // Dịch vụ gọi API
    private ProductService productService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);


        listViewGioHang = findViewById(R.id.listViewGioHang);
        gioHangItems = new ArrayList<>();

        // Ánh xạ ListView từ layout
        listViewGioHang = findViewById(R.id.listViewGioHang);
        // Khởi tạo và thiết lập adapter cho ListView
        adapter = new CustomListGioHangAdapter(this, gioHangItems);
        listViewGioHang.setAdapter(adapter);
        cartItemService = ApiClient.getRetrofitInstance().create(CartItemService.class);
        productService = ApiClient.getRetrofitInstance().create(ProductService.class);

        butTonXoaSanPham = findViewById(R.id.butTonXoaSanPham);
        butTonMuaSanPhan = findViewById(R.id.butTonMuaSanPhan);

        fetchProducts();
        setEvent();

        MenuUtils.setMenuButton(this);
    }
    private void fetchProducts(  ) {
        Call<List<Cart_item>> call = cartItemService.getAll(); // Gọi API
        call.enqueue(new Callback<List<Cart_item>>() {

            @Override
            public void onResponse(Call<List<Cart_item>> call, Response<List<Cart_item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    gioHangItems.addAll(response.body()); // tất cả vào danh sách

                    adapter.notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi

                } else {
                    Log.e("API_CALL", "Response is not successful or body is null");
                }
            }
            @Override
            public void onFailure(Call<List<Cart_item>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch products", t);
            }
        });
    }
    private void setEvent() {
        // danh sách id được chọn, checkBox á

        ArrayList<Integer> selectedPositions = adapter.getSelectedPositions();
        // Thêm sự kiện click vào nút xóa hàng
        butTonXoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy danh sách các vị trí được chọn từ adapter
                for (Integer position : selectedPositions) {
                    // Lấy ID của sản phẩm cần xóa
                    int getID = gioHangItems.get(position).getId();
                    delete1SanPham(getID);
                    Intent intent = new Intent(GioHangActivity.this, GioHangActivity.class);
                    startActivity(intent);
                    finish(); // Finish the current activit
                }
            }
        });

        // Thêm sự kiện click vào nút giỏ hàng
        butTonMuaSanPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ArrayList<Integer> selectedPositions = adapter.getSelectedPositions();

                tongTien = 0; // Đặt lại tổng tiền về 0 trước khi tính toán
                // Sử dụng AtomicInteger để đảm bảo tất cả các lệnh gọi API đều hoàn tất
                AtomicInteger remainingCalls = new AtomicInteger(selectedPositions.size());

                ArrayList<Integer> spPhuHopList = new ArrayList<>(); // Khởi tạo ArrayList
                for (Integer position : selectedPositions) {
                    int getProductID = gioHangItems.get(position).getProduct_id();
                    int getQuantiTyGioHang = gioHangItems.get(position).getQuantity();

                    Call<List<Product>> getProductCall = productService.get(getProductID);
                    getProductCall.enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                List<Product> listProduct = response.body();

                                for (Product product : listProduct) {
                                    // ko đủ trả về false
                                    if (getQuantiTyGioHang > product.getQuantity()) {
                                        Toast.makeText(v.getContext(), "Số lượng sản phẩm " + product.getName() + " còn " + product.getQuantity(), Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    // đủ sp
                                    spPhuHopList.add(1);
                                }
                                // nếu đi qua hết mà nó ko có lỗi gì thì thực hiện
                                if(spPhuHopList.size()== selectedPositions.size()){
                                    processNextSteps(v, selectedPositions);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {
                            if (remainingCalls.decrementAndGet() == 0) {
                                // Tất cả các lệnh gọi API đã hoàn tất
                                Intent intent = new Intent(v.getContext(), ThanhToanActivity.class);
                                intent.putExtra("tongTien", tongTien);
                                v.getContext().startActivity(intent);
                            }
                        }
                    });
                }
            }
        });




    }
    private void processNextSteps(View v,ArrayList<Integer> selectedPositions) {
        for (Integer position : selectedPositions) {
            int getID = gioHangItems.get(position).getId();
            int getProductID = gioHangItems.get(position).getProduct_id();
            int getQuantiTyGioHang = gioHangItems.get(position).getQuantity();
            Call<List<Product>> getProductCall = productService.get(getProductID);
            getProductCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Product> listProduct = response.body();
                        for (Product product : listProduct) {
                            tongTien = tongTien + (getQuantiTyGioHang * product.getPrice().intValue());
                            updateProductQuantity(getProductID, product.getQuantity() - getQuantiTyGioHang);
                        }
                        delete1SanPham(getID);
                        Intent intent = new Intent(v.getContext(), ThanhToanActivity.class);
                        intent.putExtra("tongTien", tongTien);
                        v.getContext().startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                }
            });
        }
    }

    private void delete1SanPham(int getID){
        // thực hiện xóa
        Call < Void > call = cartItemService.delete(getID);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API_CALL", "Failed to delete product. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_CALL", "Failed to delete product", t);
            }
        });
    }

    private void updateProductQuantity(int productId, int newQuantity) {
        // Khởi tạo một đối tượng QuantityUpdate với số lượng mới
        QuantityUpdate quantityUpdate = new QuantityUpdate(newQuantity);

        // Gọi phương thức PATCH để cập nhật số lượng sản phẩm
        Call<Cart_item> call = productService.updateQuantity(productId, quantityUpdate);

        // Thực hiện yêu cầu bất đồng bộ
        call.enqueue(new Callback<Cart_item>() {
            @Override
            public void onResponse(Call<Cart_item> call, Response<Cart_item> response) {
                if (response.isSuccessful()) {
                    // Xử lý khi cập nhật thành công
                } else {
                    // Xử lý khi cập nhật thất bại
                }
            }

            @Override
            public void onFailure(Call<Cart_item> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra
            }
        });
    }




}
