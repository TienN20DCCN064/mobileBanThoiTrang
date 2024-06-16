package com.example.quanlysach.activity;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.quanlysach.utils.MenuItem;

public class MenuClickListener implements View.OnClickListener {
    private Context mContext;
    private MenuItem mMenuItem;

    public MenuClickListener(Context context, MenuItem menuItem) {
        mContext = context;
        mMenuItem = menuItem;
    }

    @Override
    public void onClick(View v) {
        // Lấy thông điệp từ MenuItem
        String message = mMenuItem.getButtonText();
        if (message != null) {
            // Hiển thị thông điệp
            Toast.makeText(mContext, message + " clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
