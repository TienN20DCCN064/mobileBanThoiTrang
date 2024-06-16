package com.example.quanlysach.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.utils.MenuUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TrangChuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);


        MenuUtils.setMenuButton(this);
        setMenuLoaiSanPham();
    }

    // Phương thức để thiết lập sự kiện cho các button menu

    // Phương thức để thiết lập sự kiện cho các button trong GridLayout
    // Phương thức để thiết lập sự kiện cho các button trong GridLayout
    private void setMenuLoaiSanPham() {
        GridLayout gridLayout = findViewById(R.id.listLoaiSanPham);
        ImageButton imageButton_menuDayDu = findViewById(R.id.imageButton_menuDayDu);


        int soLuongLoaiSanPham = gridLayout.getChildCount();
        Log.d("SoLuongLoaiSanPham", String.valueOf(soLuongLoaiSanPham));


        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            RelativeLayout relativeLayout = (RelativeLayout) gridLayout.getChildAt(i);
            ImageButton imageButton = relativeLayout.findViewById(getResources().getIdentifier(
                    "imageButton_menu" + i / 3 + i % 3, "id", getPackageName()));
            TextView textView = relativeLayout.findViewById(getResources().getIdentifier(
                    "textView_menu" + i / 3 + i % 3, "id", getPackageName()));

            final String loaiSanPham = textView.getText().toString();

            imageButton_menuDayDu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String loaiSanPhamDayDu = "Xem đầy đủ:";
                    // Xử lý sự kiện khi click vào imageButton_menuDayDu
                    Intent intent = new Intent(TrangChuActivity.this, ChiTietLoaiMatHangActivity.class);
                    Drawable drawable = imageButton_menuDayDu.getDrawable();

                    // Kiểm tra xem Drawable có phải là BitmapDrawable không
                    String imagePath = null;
                    if (drawable instanceof BitmapDrawable) {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

                        // Lấy Bitmap từ BitmapDrawable
                        Bitmap bitmap = bitmapDrawable.getBitmap();

                        // Lưu trữ Bitmap vào một tệp và nhận đường dẫn của tệp
                        imagePath = saveBitmapToFile(bitmap);

                        // Log đường dẫn
                        Log.e("Image Path", imagePath);
                    }

                    intent.putExtra("LoaiSanPham", loaiSanPhamDayDu);
                    intent.putExtra("ImageLoaiSanPham", imagePath);
                    startActivity(intent);
                }
            });

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Chuyển sang ChiTietLoaiSachActivity và truyền tên loại sách
                    Intent intent = new Intent(TrangChuActivity.this, ChiTietLoaiMatHangActivity.class);
                    // Lấy Drawable từ ImageButton
                    Drawable drawable = imageButton.getDrawable();

                    // Kiểm tra xem Drawable có phải là BitmapDrawable không
                    String imagePath = null;
                    if (drawable instanceof BitmapDrawable) {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

                        // Lấy Bitmap từ BitmapDrawable
                        Bitmap bitmap = bitmapDrawable.getBitmap();

                        // Lưu trữ Bitmap vào một tệp và nhận đường dẫn của tệp
                        imagePath = saveBitmapToFile(bitmap);

                        // Log đường dẫn
                        Log.e("Image Path", imagePath);
                    }

                    intent.putExtra("LoaiSanPham", loaiSanPham);
                    intent.putExtra("ImageLoaiSanPham", imagePath);
                    //intent.putExtra("")
                    startActivity(intent);
                }
            });
        }
    }

    private String saveBitmapToFile(Bitmap bitmap) {
        // Lưu trữ Bitmap vào thư mục cache của ứng dụng
        String imagePath = getExternalCacheDir() + "/image_" + System.currentTimeMillis() + ".png";
        File file = new File(imagePath);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Trả về đường dẫn của tệp
        return imagePath;
    }
}
