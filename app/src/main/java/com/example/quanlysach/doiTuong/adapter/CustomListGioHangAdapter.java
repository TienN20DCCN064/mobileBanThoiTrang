package com.example.quanlysach.doiTuong.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlysach.R;
import com.example.quanlysach.api.ApiClient;
import com.example.quanlysach.api.CartItemService;
import com.example.quanlysach.api.ProductService;
import com.example.quanlysach.model.Cart_item;
import com.example.quanlysach.model.ImageDataSanPham;
import com.example.quanlysach.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomListGioHangAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Cart_item> gioHangListItems;
    private ProductService productService;
    private CartItemService cartItemService;
    private ArrayList<Integer> checkedPositions; // Thêm mảng này để lưu trạng thái của checkbox


    public CustomListGioHangAdapter(Context context, ArrayList<Cart_item> gioHangItems) {
        this.context = context;
        this.gioHangListItems = gioHangItems;
        this.checkedPositions = new ArrayList<>(); // Khởi tạo mảng trạng thái checkbox
        this.productService = ApiClient.getRetrofitInstance().create(ProductService.class);
        this.cartItemService = ApiClient.getRetrofitInstance().create(CartItemService.class);


    }

    @Override
    public int getCount() {
        return gioHangListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_gio_dang_1loai, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        Cart_item cart_item = gioHangListItems.get(position);
        holder.bind(cart_item, position);
        // Tạm thời loại bỏ listener để tránh kích hoạt không mong muốn
        holder.checkBoxChon.setOnCheckedChangeListener(null);

        // Cập nhật trạng thái của CheckBox
        holder.checkBoxChon.setChecked(checkedPositions.contains(position));
        // Đặt sự kiện cho CheckBox
        // Đặt lại listener sau khi cập nhật trạng thái
        holder.checkBoxChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!checkedPositions.contains(position)) {
                        checkedPositions.add(position); // Nếu được chọn, thêm vị trí vào mảng
                    }
                } else {
                    checkedPositions.remove(Integer.valueOf(position)); // Nếu không được chọn, loại bỏ vị trí khỏi mảng
                }
            }
        });

        return convertView;
    }

    public ArrayList<Integer> getSelectedPositions() {
        return checkedPositions;
    }

    private class ViewHolder {
        ImageView imageViewSanPham;
        ImageButton btnMinus, btnPlus;
        TextView tvSoLuong1SpTrongGio, textViewTenSanPham, textViewGiaBanSanPham;
        int position;
        CheckBox checkBoxChon;


        ViewHolder(View view) {
            imageViewSanPham = view.findViewById(R.id.imageViewSanPham);
            btnMinus = view.findViewById(R.id.btnMinus);
            btnPlus = view.findViewById(R.id.btnPlus);
            tvSoLuong1SpTrongGio = view.findViewById(R.id.tvSoLuong1SpTrongGio);
            textViewTenSanPham = view.findViewById(R.id.textViewTenSanPham);
            textViewGiaBanSanPham = view.findViewById(R.id.textViewGiaBanSanPham);
            checkBoxChon = view.findViewById(R.id.checkBoxChon); // Ánh xạ checkBoxChon từ layout
            setupMinusButton();
            setupPlusButton();

        }

        void bind(Cart_item cart_item, int position) {
            this.position = position;
            tvSoLuong1SpTrongGio.setText(String.valueOf(cart_item.getQuantity()));
            fetchProductDetails(cart_item.getProduct_id());

        }



        private void fetchProductDetails(int productId) {

            Call<List<Product>> getProductCall = getProduct(productId);
            getProductCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Product> products = response.body();
                        for (Product product : products) {
//                            if (product.getId() == productId) {
                                textViewTenSanPham.setText(product.getName());
                                textViewGiaBanSanPham.setText("Giá : " +product.getPrice()+" $");
                                setImageViewFromData(product.getImageData());
                                return;
                         //   }
                        }
                        textViewTenSanPham.setText("Tên sản phẩm không xác định");
                        textViewGiaBanSanPham.setText("Giá không xác định");
                    } else {
                        textViewTenSanPham.setText("Tên sản phẩm không xác định");
                        textViewGiaBanSanPham.setText("Giá không xác định");
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    textViewTenSanPham.setText("Lỗi khi lấy tên sản phẩm");
                    textViewGiaBanSanPham.setText("Lỗi khi lấy giá sản phẩm");
                    Log.e("Lỗi", "Failed to update quantity", t);
                }
            });
        }

        private void setImageViewFromData(ImageDataSanPham imageData) {
            if (imageData != null && imageData.getData() != null) {
                byte[] imageDataBytes = new byte[imageData.getData().size()];
                for (int i = 0; i < imageData.getData().size(); i++) {
                    imageDataBytes[i] = imageData.getData().get(i).byteValue();
                }
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageDataBytes, 0, imageDataBytes.length);
                imageViewSanPham.setImageBitmap(bitmap);
            } else {
                imageViewSanPham.setImageResource(R.drawable.image_admin);
            }
        }

        private void setupMinusButton() {
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decreaseQuantity();
                }
            });
        }

        private void setupPlusButton() {
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    increaseQuantity();
                }
            });
        }

        private void decreaseQuantity() {
            int quantity = Integer.parseInt(tvSoLuong1SpTrongGio.getText().toString());
            if (quantity > 1) {
                quantity--;
                tvSoLuong1SpTrongGio.setText(String.valueOf(quantity));
                updateCartItemQuantity(quantity,position);
            }
        }

        private void increaseQuantity() {
            int quantity = Integer.parseInt(tvSoLuong1SpTrongGio.getText().toString());
            quantity++;
            tvSoLuong1SpTrongGio.setText(String.valueOf(quantity));
            updateCartItemQuantity(quantity,position);
        }

        private void updateCartItemQuantity(int newQuantity, int position) {
            Cart_item cartItem = gioHangListItems.get(position);
            // Tạo một đối tượng QuantityUpdate chỉ chứa quantity mới
            QuantityUpdate quantityUpdate = new QuantityUpdate(newQuantity);

            Call<Cart_item> updateCartItemCall = cartItemService.updateQuantity(cartItem.getId(), quantityUpdate);
            Log.e("quantity",quantityUpdate.toString());
            updateCartItemCall.enqueue(new Callback<Cart_item>() {
                @Override
                public void onResponse(Call<Cart_item> call, Response<Cart_item> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        Cart_item cartItem = gioHangListItems.get(position);

                        // Cập nhật quantity
                        cartItem.setQuantity(newQuantity);

                        // Cập nhật lại dữ liệu trong gioHangListItems
                        gioHangListItems.set(position, cartItem);

                        // Thông báo cho Adapter biết rằng dữ liệu đã thay đổi
                        notifyDataSetChanged();

                        Log.e("ok", cartItem.toString());
                        // Thông báo cho Adapter biết rằng dữ liệu đã thay đổi
                    }
                }

                @Override
                public void onFailure(Call<Cart_item> call, Throwable t) {
                    Log.e("Lỗi", "Failed to update quantity", t);
                }
            });
        }
        private Call<List<Product>> getProduct(int productId) {
            return productService.get(productId);
        }


    }
}




