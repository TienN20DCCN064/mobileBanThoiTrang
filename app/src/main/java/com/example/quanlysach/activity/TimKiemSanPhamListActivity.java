package com.example.quanlysach.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.ProductService;
import com.example.quanlysach.doiTuong.adapter.CustomAdapter;
import com.example.quanlysach.model.Product;
import com.example.quanlysach.utils.MenuUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemSanPhamListActivity extends AppCompatActivity {
    private List<Product> itemList; // Danh sách sản phẩm
    private ProductService productService; // Dịch vụ gọi API
    private ListView listViewSanPhamTimKiem; // ListView để hiển thị sản phẩm
    private CustomAdapter adapter; // Adapter cho ListView
    private TextView tvTimKiemTheo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tim_kiem_san_pham);
        tvTimKiemTheo = findViewById(R.id.tvTimKiemTheo);

        // Nhận Intent từ Activity trước (TimKiemSanPhamActivity)
        Intent intent = getIntent();

        // Kiểm tra xem Intent có tồn tại và có chứa giá trị "SEARCH_TEXT" hay không
        if (intent != null && intent.hasExtra("SEARCH_TEXT")) {
            // Lấy giá trị "SEARCH_TEXT" ra từ Intent
            String searchText = intent.getStringExtra("SEARCH_TEXT");
            // Hiển thị giá trị "SEARCH_TEXT" lên TextView
            tvTimKiemTheo.setText(searchText);

            listViewSanPhamTimKiem = findViewById(R.id.listViewSanPhamTimKiem);
            itemList = new ArrayList<>();
            adapter = new CustomAdapter(this, itemList,tvTimKiemTheo.toString());
            listViewSanPhamTimKiem.setAdapter(adapter); // Gán adapter cho ListView
            // Khởi tạo ProductService từ ApiClient
            productService = ApiClient.getRetrofitInstance().create(ProductService.class);
            // Gọi hàm fetchProducts để lấy dữ liệu từ API
            fetchProducts(tvTimKiemTheo.getText().toString());

        }

        MenuUtils.setMenuButton(this);
    }
    private void fetchProducts(String tvTimKiemTheo ) {
        Call<List<Product>> call = productService.getAll(); // Gọi API
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> listProduct = response.body(); // Lưu tất cả sản phẩm từ API vào listProduct

                    for (Product product : listProduct) {
                        // Kiểm tra điều kiện và thêm sản phẩm vào danh sách hiển thị nếu điều kiện đúng
                        if (checkIn(tvTimKiemTheo, product.getName())) {
                            Log.e("thanh cong",product.toString());
                            itemList.add(product);
                        }
                        else {
                            Log.e("thanh Bai",product.toString());
                        }
                    }
                    adapter.notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi

                } else {
                    Log.e("API_CALL", "Response is not successful or body is null");
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch products", t);
            }
        });
    }

    private boolean checkIn(String giaTriCanTim, String giaTriCanKiemTra){
        // Chuyển cả hai chuỗi về chữ thường và loại bỏ các ký tự đặc biệt

        String cleanGiaTriCanTim = giaTriCanTim.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        String cleanGiaTriCanKiemTra = giaTriCanKiemTra.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        String input1 = Normalizer.normalize(cleanGiaTriCanTim, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
        String input2 = Normalizer.normalize(cleanGiaTriCanKiemTra, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
        if (input1.contains(input2) || input2.contains(input1)) {
            return true;
        }


        // So sánh hai chuỗi đã được làm sạch
        return false;
    }
    // lọc danh sách theo loaiSanPham

    // xóa kí tự
    private String displayLoaiSanPham(String loaiSanPham) {
        // Loại bỏ các ký tự đặc biệt và chuyển về chữ thường
        String normalized = Normalizer.normalize(loaiSanPham, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
        // Loại bỏ khoảng trắng ở đầu và cuối chuỗi
        normalized = normalized.trim();
        // Thay thế "Đ" thành "D"
        normalized = normalized.replace("đ", "d");
        // in hoa lên
        return normalized.toUpperCase();
    }
}