package com.example.quanlysach.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.CartItemService;
import com.example.quanlysach.api.ProductService;
import com.example.quanlysach.model.Cart_item;
import com.example.quanlysach.model.ImageDataSanPham;
import com.example.quanlysach.model.Product;
import com.example.quanlysach.utils.MenuUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChiTiet1MatHangActivity extends AppCompatActivity {
    // Khai báo các thành phần giao diện ở đây
    TextView textViewGiaSanPham;
    TextView textViewTenSanPham;
    ImageView imageViewAnhSanPham;
   ImageButton btnBackChiTietLoaiMatHang;
    Button btnMuaSanPham;

    TextView textViewTenSanPham1;
    TextView textViewLoaiSanPham1;
    TextView textViewMieuTa1;
    TextView textViewSoLuong1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        // Ánh xạ các thành phần giao diện từ layout XML
        textViewGiaSanPham = findViewById(R.id.textViewGiaSanPham);
        textViewTenSanPham = findViewById(R.id.textViewTenSanPham);
        imageViewAnhSanPham = findViewById(R.id.imageViewAnhSanPham);
        btnBackChiTietLoaiMatHang = findViewById(R.id.btnBackChiTietLoaiMatHang);
        btnMuaSanPham = findViewById(R.id.btnMuaSanPham);
        // Ánh xạ các view từ layout XML vào biến trong class
        textViewTenSanPham1 = findViewById(R.id.textViewTenSanPham1);
        textViewLoaiSanPham1 = findViewById(R.id.textViewLoaiSanPham1);
        textViewMieuTa1 = findViewById(R.id.textViewMieuTa1);
        textViewSoLuong1 = findViewById(R.id.textViewSoLuong1);

        // Nhận giá trị ID của sản phẩm từ Intent
        int productId = getIntent().getIntExtra("ProductId", -1); // -1 là giá trị mặc định nếu không tìm thấy giá trị
        if (productId != -1) {
            getProductById(productId);
            setEvent(productId);
        }
      //  MenuUtils.setMenuButton(this);  // button home
    }

    private void getProductById(int productId) {
        // Khởi tạo ProductService từ ApiClient
        ProductService productService = ApiClient.getRetrofitInstance().create(ProductService.class);

        // Gọi phương thức get(id) với productId được truyền vào
        Call<List<Product>> call = productService.get(productId);

        // Sử dụng enqueue để thực hiện cuộc gọi bất đồng bộ
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    // Nhận danh sách sản phẩm từ response
                    List<Product> productList = response.body();
                    Log.e("Tien",productList.toString());
                    // Lấy sản phẩm đầu tiên từ danh sách (do phương thức get(id) trả về một danh sách sản phẩm)
                    Product product = productList.get(0);
                    // Tiến hành xử lý dữ liệu nhận được, ví dụ, gán giá trị vào TextView
                    textViewGiaSanPham.setText("Giá SP: " + product.getPrice() + "$");
                    textViewTenSanPham.setText(product.getName());
                    textViewTenSanPham1.setText("Tên sp: "+product.getName());

                    textViewLoaiSanPham1.setText("Loai sp: "+product.getCategory());
                    textViewMieuTa1.setText("Miêu tả: "+product.getDescription());
                    textViewSoLuong1.setText("Số Lượng : "+product.getQuantity());


                    // Lấy dữ liệu hình ảnh từ đối tượng Product
                    ImageDataSanPham imageData = product.getImageData();

                    // Hiển thị hình ảnh nếu có
                    if (imageData != null) {
                        List<Integer> imageBytes = imageData.getData();
                        if (imageBytes != null && !imageBytes.isEmpty()) {
                            // Chuyển đổi danh sách integer sang mảng byte
                            byte[] bytes = new byte[imageBytes.size()];
                            for (int i = 0; i < imageBytes.size(); i++) {
                                bytes[i] = (byte) (int) imageBytes.get(i);
                            }
                            // Chuyển đổi mảng byte sang Bitmap
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imageViewAnhSanPham.setImageBitmap(bitmap);
                        } else {
                            // Nếu không có dữ liệu hình ảnh, đặt hình ảnh mặc định
                            imageViewAnhSanPham.setImageResource(R.drawable.image_menu1); // Đảm bảo bạn có tệp default_image trong thư mục drawable
                        }
                    } else {
                        // Nếu không có dữ liệu hình ảnh, đặt hình ảnh mặc định
                        imageViewAnhSanPham.setImageResource(R.drawable.image_menu1); // Đảm bảo bạn có tệp default_image trong thư mục drawable
                    }

                } else {
                    // Xử lý khi không nhận được dữ liệu từ server hoặc ID không tồn tại
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra trong quá trình gọi API
            }
        });
    }


    private void setEvent(int productId) {
        // Bắt sự kiện khi nút "Quay lại" được nhấn
        btnBackChiTietLoaiMatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại trang trước đó
            }
        });

        // Bắt sự kiện khi nút "Mua sản phẩm" được nhấn
        btnMuaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo ProductService từ ApiClient
                ProductService productService = ApiClient.getRetrofitInstance().create(ProductService.class);
                CartItemService cartItemService = ApiClient.getRetrofitInstance().create(CartItemService.class);
                Call<List<Product>> call = productService.get(productId);
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            // Xử lý dữ liệu sản phẩm được nhận
                            List<Product> productList = response.body();
                            Product product = productList.get(0);
                            Cart_item cartItem = new Cart_item();
                            cartItem.setSession_id(1); // sau xóa cái này đi
                            cartItem.setProduct_id(product.getId());
                            cartItem.setQuantity(1);
                            cartItem.setCreated_at("2024-06-05T09:22:21.000Z");
                            cartItem.setModified_at("2024-06-05T09:22:21.000Z");
                            Call<List<Cart_item>> callAllCart_item = cartItemService.getAll();
                            callAllCart_item.enqueue(new Callback<List<Cart_item>>() {
                                @Override
                                public void onResponse(Call<List<Cart_item>> call, Response<List<Cart_item>> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        // Xử lý dữ liệu danh sách mục trong giỏ hàng được nhận
                                        List<Cart_item> cartItemList = response.body();
                                        // Duyệt qua từng đối tượng trong danh sách cartItemList
                                        Boolean checkTonTaiProducId = false;
                                        for (Cart_item cartItem : cartItemList) {
                                            // Kiểm tra xem getProduct_id của cartItem có bằng với product.getId() không
                                            if (cartItem.getProduct_id() == product.getId()) {
                                                checkTonTaiProducId = true;
                                                break;
                                            }
                                        }
                                        // ko có cái nào đã tồi tại // thì thêm vào giỏ hàng
                                        if (checkTonTaiProducId == false){
                                            Call<Cart_item> createCall = cartItemService.create(cartItem);
                                            createCall.enqueue(new Callback<Cart_item>() {
                                                @Override
                                                public void onResponse(Call<Cart_item> call, Response<Cart_item> response) {
                                                    Intent intent = new Intent(ChiTiet1MatHangActivity.this, GioHangActivity.class);
                                                    startActivity(intent);

                                                }
                                                @Override
                                                public void onFailure(Call<Cart_item> call, Throwable t) {

                                                }
                                            });
                                        }
                                        // đã có rồi  /// chưa code phần này để hiện thị ra
                                        else {
                                            // thì sẽ đi đến luôn  // lấy cái khóa để click vào
                                            Intent intent = new Intent(ChiTiet1MatHangActivity.this, GioHangActivity.class);
                                            intent.putExtra("buyProductId", product.getId());
                                            startActivity(intent);

                                        }
                                    } else {
                                    }
                                }
                                @Override
                                public void onFailure(Call<List<Cart_item>> call, Throwable t) {
                                    // Xử lý khi có lỗi xảy ra trong quá trình gọi API
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });


            }
        });
    }




}
