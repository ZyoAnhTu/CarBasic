package fpoly.edu.carproject.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.carproject.database.DbHelper;
import fpoly.edu.carproject.model.NhanVien;

public class NhanVienDAO {
    private SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert (NhanVien obj) {
        ContentValues values = new ContentValues();
//        values.put("maNV",obj.getMaNV());
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        values.put("soDT",obj.getSoDT());
        return db.insert("NhanVien",null,values);
    }
    public int update(NhanVien obj) {
        ContentValues values = new ContentValues();

        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        values.put("soDT",obj.getSoDT());
        return db.update("NhanVien",values,"maNV=?",new String[]{String.valueOf(obj.getMaNV())});
    }
    public int delete(String id) {
        return db.delete("NhanVien","maNV=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<NhanVien> getData (String sql, String...selectionArgs) {

        List<NhanVien> list = new ArrayList<NhanVien>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()) {
            NhanVien obj = new NhanVien();
            obj.setMaNV(Integer.parseInt(c.getString(c.getColumnIndex("maNV"))));
            try {
            }catch (Exception e) {
            }
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            obj.setSoDT(Integer.parseInt(c.getString(c.getColumnIndex("soDT"))));
//            Log.i("//======",obj.toString());
            list.add(obj);
        }
        return list;
    }
    public List<NhanVien> getAll() {
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }
    public NhanVien getID (String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        List<NhanVien> list = getData(sql,id);
        return list.get(0);
    }
}
