package fpoly.edu.carproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.edu.carproject.model.Xe;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ATCar";
    private static final int DB_VERSION = 1;

    static final String CREATE_TABLE_CHUCUAHANG = "CREATE TABLE ChuCuaHang (" +
            "    maCCH text PRIMARY KEY," +
            "    hoTen text NOT NULL," +
            "    matKhau text NOT NULL" +
            ");";

    static final String CREATE_TABLE_NHANVIEN = "CREATE TABLE NhanVien(" +
            "    maNV integer PRIMARY KEY AUTOINCREMENT," +
            "    hoTen text NOT NULL," +
            "    namSinh text NOT NULL," +
            "    soDT integer NOT NULL" +
            ");";

    static final String CREATE_TABLE_LOAIXE = "CREATE TABLE LoaiXe(" +
            "    maLoaiXe integer PRIMARY KEY AUTOINCREMENT ," +
            "    tenLoaiXe text NOT NULL" +
            ");";

    static final String CREATE_TABLE_XE = "CREATE TABLE Xe(" +
            "    maXe integer PRIMARY KEY AUTOINCREMENT," +
            "    tenXe text NOT NULL," +
            "    dongXe text NOT NULL," +
            "    nhienLieu text NOT NULL," +
            "    giaBan integer NOT NULL," +
            "    maLoaiXe integer REFERENCES LoaiXe (maLoaiXe)" +
            ");";

    static final String CREATE_TABLE_DONHANG = "CREATE TABLE DonHang( " +
            "    maDH integer PRIMARY KEY AUTOINCREMENT," +
            "    maXe integer REFERENCES Xe (maXe)," +
            "    maNV integer REFERENCES NhanVien(maNV)," +
            "    ngay date NOT NULL," +
            "    gia integer NOT NULL," +
            "    maCCH text REFERENCES ChuCuaHang(maCCH)" +
            ");";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHUCUAHANG);
        db.execSQL(CREATE_TABLE_NHANVIEN);
        db.execSQL(CREATE_TABLE_LOAIXE);
        db.execSQL(CREATE_TABLE_XE);
        db.execSQL(CREATE_TABLE_DONHANG);

        db.execSQL(Data_SQLite.INSERT_CHU_CUA_HANG);
        db.execSQL(Data_SQLite.INSERT_NHAN_VIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_XE);
        db.execSQL(Data_SQLite.INSERT_XE);
        db.execSQL(Data_SQLite.INSERT_DON_HANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableChuCuaHang = "drop table if exists ChuCuaHang";
        db.execSQL(dropTableChuCuaHang);
        String dropTableNhanVien = "drop table if exists NhanVien";
        db.execSQL(dropTableNhanVien);
        String dropTableLoaiXe = "drop table if exists LoaiXe";
        db.execSQL(dropTableLoaiXe);
        String dropTableXe = "drop table if exists Xe";
        db.execSQL(dropTableXe);
        String dropTableDonHang = "drop table if exists DonHang";
        db.execSQL(dropTableDonHang);

        onCreate(db);
    }
}
