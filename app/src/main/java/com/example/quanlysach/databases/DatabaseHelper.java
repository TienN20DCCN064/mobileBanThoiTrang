package com.example.quanlysach.databases;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tienDATA.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột
    public static final String TABLE_DATABASE = "database";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_COVER_IMAGE = "cover_image";

    // Câu lệnh tạo bảng
    private static final String SQL_CREATE_DATABASE_TABLE =
            "CREATE TABLE " + TABLE_DATABASE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_AUTHOR + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_PRICE + " REAL NOT NULL," +
                    COLUMN_CATEGORY + " TEXT," +
                    COLUMN_COVER_IMAGE + " BLOB)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Thực thi câu lệnh tạo bảng khi cần thiết
        db.execSQL(SQL_CREATE_DATABASE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu nếu cần
        // Hiện tại không có nhu cầu nâng cấp, có thể để trống
    }
}
