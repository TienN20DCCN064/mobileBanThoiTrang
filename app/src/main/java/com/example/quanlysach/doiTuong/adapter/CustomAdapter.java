package com.example.quanlysach.doiTuong.adapter;
import android.os.Handler;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlysach.R;
import com.example.quanlysach.activity.ChiTiet1MatHangActivity;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.CartItemService;
import com.example.quanlysach.model.Cart_item;
import com.example.quanlysach.model.ImageDataSanPham;
import com.example.quanlysach.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

// CustomAdapter.java

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<Product> listProduct;
    private LayoutInflater inflater;
    private String loaiSanPham; // Thêm biến để hiển thị theo loaiSanPham


    public CustomAdapter(Context context, List<Product> listProduct, String loaiSanPham) {
        this.context = context;
        this.listProduct = listProduct;
        this.loaiSanPham = loaiSanPham; // Lưu trữ loại sản phẩm
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list_chi_tiet_loai_sp_only, parent, false);
            holder = new ViewHolder();
            holder.imageButtonSanPham = convertView.findViewById(R.id.imageButtonSanPham);
            holder.btnThemVaoGioHang = convertView.findViewById(R.id.btnThemVaoGioHang);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageButtonSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = listProduct.get(position);
                if (product != null) {
                    Intent intent = new Intent(context, ChiTiet1MatHangActivity.class);
                    intent.putExtra("ProductId", product.getId());
                    context.startActivity(intent);
                }
            }
        });
        holder.btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = listProduct.get(position);
                if (product != null ) {
                    // Kiểm tra số lượng sản phẩm
                    // Lấy đối tượng Retrofit từ ApiClient
                    Retrofit retrofit = ApiClient.getRetrofitInstance();
                    CartItemService service = retrofit.create(CartItemService.class);
                    // Gọi phương thức để lấy danh sách tất cả các mục trong giỏ hàng
                    Call<List<Cart_item>> call = service.getAll();
                    call.enqueue(new Callback<List<Cart_item>>() {
                        @Override
                        public void onResponse(Call<List<Cart_item>> call, Response<List<Cart_item>> response) {
                            if (response.isSuccessful()) {
                                List<Cart_item> cartItems = response.body();
                                boolean productExistsInCart = false;
                                for (Cart_item cartItem : cartItems) {
                                    if (cartItem.getProduct_id() == product.getId()) {
                                        productExistsInCart = true;
                                        break;
                                    }
                                }
                                if (!productExistsInCart) {
                                    // Nếu sản phẩm không tồn tại trong giỏ hàng, thêm mới vào giỏ hàng
                                    Cart_item cartItem = new Cart_item();
                                    cartItem.setSession_id(1); // sau xóa cái này đi
                                    cartItem.setProduct_id(product.getId());
                                    cartItem.setQuantity(1);
                                    cartItem.setCreated_at("2024-06-05T09:22:21.000Z");
                                    cartItem.setModified_at("2024-06-05T09:22:21.000Z");

                                    // Thêm đối tượng mới vào giỏ hàng
                                    Call<Cart_item> createCall = service.create(cartItem);
                                    createCall.enqueue(new Callback<Cart_item>() {
                                        @Override
                                        public void onResponse(Call<Cart_item> call, Response<Cart_item> response) {
                                            Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<Cart_item> call, Throwable t) {
                                            // Xử lý khi gặp lỗi
                                            Log.e("Cart", "Error: " + t.getMessage());
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(context, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();

                                }


                            } else {
                                // Xử lý phản hồi lỗi
                                Log.e("Cart", "Failed to fetch cart items!");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Cart_item>> call, Throwable t) {
                            // Xử lý khi gặp l
                            Log.e("Cart", "Error: " + t.getMessage());
                        }
                    });
                }
            }
        });



        // Thiết lập thông tin của item tại vị trí position
        Product product = listProduct.get(position);

            // hình ảnh
            ImageDataSanPham imageData = product.getImageData();
            if (imageData != null) {
                List<Integer> imageBytes = imageData.getData();
                if (imageBytes != null && !imageBytes.isEmpty()) {
                    byte[] bytes = new byte[imageBytes.size()];
                    for (int i = 0; i < imageBytes.size(); i++) {
                        bytes[i] = (byte) (int) imageBytes.get(i);
                    }
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.imageButtonSanPham.setImageBitmap(bitmap);
                } else {
                    holder.imageButtonSanPham.setImageResource(R.drawable.image_menu1);
                }
            } else {
                holder.imageButtonSanPham.setImageResource(R.drawable.image_menu1);
            }

            TextView textViewTenSanPham = convertView.findViewById(R.id.textViewTenSanPham);
            TextView textViewLoaiSanPham = convertView.findViewById(R.id.textViewLoaiSanPham);
            TextView textViewMieuTaSanPham = convertView.findViewById(R.id.textViewMieuTaSanPham);
            TextView textViewGiaSanPham = convertView.findViewById(R.id.textViewGiaSanPham);

            textViewTenSanPham.setText(product.getName());
            textViewLoaiSanPham.setText("Loai: " + product.getCategory());
            textViewMieuTaSanPham.setText("Miêu tả: " + product.getDescription());
            textViewGiaSanPham.setText("Giá : " + product.getPrice().toString() + " $");

        return convertView;
    }


    // Class ViewHolder để lưu trữ các view được tìm thấy trong findViewById để tránh việc phải tìm kiếm lại
    static class ViewHolder {
        ImageButton imageButtonSanPham;
        ImageButton btnThemVaoGioHang;

    }
}
