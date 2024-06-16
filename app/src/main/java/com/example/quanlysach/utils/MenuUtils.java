// MenuUtils.java
package com.example.quanlysach.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;
import com.example.quanlysach.activity.GioHangActivity;
import com.example.quanlysach.activity.ThongTinNguoiDungActivity;
import com.example.quanlysach.activity.TimKiemSanPhamActivity;
import com.example.quanlysach.activity.TrangChuActivity;

public class MenuUtils {

    public static void setMenuButton(AppCompatActivity activity) {
        Log.e("acc1","1");
        // Tạo danh sách các MenuItem
        MenuItem[] menuItems = {
                new MenuItem(R.id.btnHome, "Home"),
                new MenuItem(R.id.btnSearch, "Search"),
                new MenuItem(R.id.btnGioHang, "Giỏ Hàng"),
                new MenuItem(R.id.btnInformation, "Thông Tin")
        };

        // Thiết lập sự kiện click cho từng MenuItem
        for (MenuItem menuItem : menuItems) {
            ImageButton button = activity.findViewById(menuItem.getButtonId());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Lấy thông điệp từ MenuItem
                    String buttonText = menuItem.getButtonText();

                    // Xử lý sự kiện dựa trên nút được click
                    switch (buttonText) {
                        case "Home":
                            // Chuyển sang Activity HomeActivity khi click vào Home
                            Intent homeIntent = new Intent(activity, TrangChuActivity.class);
                            activity.startActivity(homeIntent);
                            break;
                        case "Search":
                            // Chuyển sang Activity TimSachActivity khi click vào Search
                            Intent searchIntent = new Intent(activity, TimKiemSanPhamActivity.class);
                            activity.startActivity(searchIntent);
                            break;
                        case "Giỏ Hàng":
                            // Chuyển sang Activity GioHangActivity khi click vào Giỏ Hàng
                            Intent gioHangIntent = new Intent(activity, GioHangActivity.class);
                            activity.startActivity(gioHangIntent);
                            break;
                        case "Thông Tin":
                            // Chuyển sang Activity ThongTinActivity khi click vào Thông Tin
                            Intent thongTinIntent = new Intent(activity, ThongTinNguoiDungActivity.class);
                            activity.startActivity(thongTinIntent);
                            break;
                        default:
                            // Hiển thị thông điệp nếu không có sự kiện nào phù hợp
                            Context context = activity.getApplicationContext();
                            Toast.makeText(context, buttonText + " clicked", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }
}
