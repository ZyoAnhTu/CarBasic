package fpoly.edu.carproject.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.carproject.database.DbHelper;
import fpoly.edu.carproject.model.ChuCuaHang;
import fpoly.edu.carproject.model.NhanVien;

public class ChuCuaHangDAO {
    DbHelper dbHelper;
    private SQLiteDatabase db;
    SharedPreferences preferences;

    public ChuCuaHangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        preferences = context.getSharedPreferences("THONGTIN",Context.MODE_PRIVATE);
    }
    public long insert (ChuCuaHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
            values.put("matKhau",obj.getMatKhau());
        return db.insert("ChuCuaHang",null,values);
    }
    public int update(ChuCuaHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.update("ChuCuaHang",values,"maCCH=?",new String[]{String.valueOf(obj.getMaCCH())});
    }
    public int updatePass (ChuCuaHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.update("ChuCuaHang",values,"maCCH = ?",new String[] {obj.getMaCCH()});
    }
    public int delete(String id) {
        return db.delete("ChuCuaHang","maCCH=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<ChuCuaHang> getData (String sql, String...selectionArgs) {

        List<ChuCuaHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()) {
            ChuCuaHang obj = new ChuCuaHang();
            obj.setMaCCH(c.getString(c.getColumnIndex("maCCH")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }
    public List<ChuCuaHang> getAll() {
        String sql = "SELECT * FROM ChuCuaHang";
        return getData(sql);
    }
    public ChuCuaHang getID (String id) {
        String sql = "SELECT * FROM ChuCuaHang WHERE maCCH=?";
        List<ChuCuaHang> list = getData(sql,id);
        return list.get(0);
    }
//    public boolean KiemTraDangNhap(String user, String pass) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM CHUCUAHANG WHERE user=? AND pass=?",new String[]{user,pass});
//        if (c.getCount() != 0 ){
//            c.moveToFirst();
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putInt("idCCH",c.getInt(0));
//return true;
//        }
//        return false;
//    }
    public int checkLogin(String user, String pass) {
        String sql = "SELECT * FROM ChuCuaHang WHERE maCCH = ? AND matKhau = ?";
        List<ChuCuaHang> list = getData(sql,user,pass);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
}
