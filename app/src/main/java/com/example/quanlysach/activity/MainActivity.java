package com.example.quanlysach.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysach.R;

// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private TextView textView;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_loai_san_pham);


    }

}