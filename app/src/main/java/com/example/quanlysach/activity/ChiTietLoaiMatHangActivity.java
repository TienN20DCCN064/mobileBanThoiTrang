package com.example.quanlysach.activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;

import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.ProductService;
import com.example.quanlysach.doiTuong.Item;
import com.example.quanlysach.doiTuong.adapter.CustomAdapter;
import com.example.quanlysach.model.Product;
import com.example.quanlysach.utils.MenuUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietLoaiMatHangActivity extends AppCompatActivity {
    private List<Product> itemList; // Danh sách sản phẩm
    private ProductService productService; // Dịch vụ gọi API
    private ListView listViewSachTest; // ListView để hiển thị sản phẩm
    private CustomAdapter adapter; // Adapter cho ListView

    //  private List<Item> itemList;
// tién111
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_loai_san_pham);

        // ListView listViewSach = findViewById(R.id.listViewSachTest);
        ImageView imageView = findViewById(R.id.imageViewButton);
        TextView loaiMatHang = findViewById(R.id.loaiMatHang);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Lấy tên loại sách từ Intent
            String loaiSanPham = extras.getString("LoaiSanPham");
            String imageLoaiSanPham = extras.getString("ImageLoaiSanPham");
            // Sử dụng imageLoaiSach để thay thế src của ImageView
            imageView.setImageURI(Uri.parse(imageLoaiSanPham));
            loaiMatHang.setText(loaiSanPham);
            listViewSachTest = findViewById(R.id.listViewSachTest);

            // Khởi tạo danh sách sản phẩm
            itemList = new ArrayList<>();

            // lưu thêm giá trị loại sản phậm để hiện thị bên kia
            adapter = new CustomAdapter(this, itemList,loaiSanPham);
            listViewSachTest.setAdapter(adapter); // Gán adapter cho ListView
            // Khởi tạo ProductService từ ApiClient
            productService = ApiClient.getRetrofitInstance().create(ProductService.class);

            // Gọi hàm fetchProducts để lấy dữ liệu từ API
            fetchProducts(loaiSanPham);
        } else {
            Log.e("ChiTietLoaiMatHangActivity", "No extras found in intent");
        }

        MenuUtils.setMenuButton(this);  // button home
    }


    // Phương thức để thêm sản phẩm vào giỏ hàng

    private void fetchProducts(  String loaiSanPham ) {
        Call<List<Product>> call = productService.getAll(); // Gọi API

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<Product> filteredList = new ArrayList<>();
                    if ("Xem đầy đủ:".equals(loaiSanPham)) {
                        // Không cần lọc, thêm tất cả vào danh sách
                        filteredList.addAll(response.body());
                    } else {
                        filteredList = filterProductsByType(response.body(), loaiSanPham);
                    }

                    // Lọc ra các sản phẩm có getQuantity() > 0
                    List<Product> validProducts = new ArrayList<>();
                    for (Product product : filteredList) {
                        if (product.getQuantity() > 0) {
                            validProducts.add(product);
                        }
                    }
                    itemList.addAll(validProducts); // Thêm danh sách sản phẩm đã lọc vào itemList
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

    // lọc danh sách theo loaiSanPham
    private List<Product> filterProductsByType(List<Product> productList, String loaiSanPham) {
        String loaiSanPhamNormalized = displayLoaiSanPham(loaiSanPham);
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            Log.e("product.getQuantity()",product.getQuantity()+"");
            if ((displayLoaiSanPham(product.getCategory()).equals(loaiSanPhamNormalized)) ) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }
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