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
import fpoly.edu.carproject.model.ChuCuaHang;
import fpoly.edu.carproject.model.LoaiXe;

public class LoaiXeDAO {
    private SQLiteDatabase db;

    public LoaiXeDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert (LoaiXe obj) {
        ContentValues values = new ContentValues();
//        values.put("maLoaiXe",obj.getMaLoaiXe());
        values.put("tenLoaiXe",obj.getTenLoaiXe());
        return db.insert("LoaiXe",null,values);
    }
    public int update(LoaiXe obj) {
        ContentValues values = new ContentValues();
//        values.put("maLoaiXe",obj.getMaLoaiXe());
        values.put("tenLoaiXe",obj.getTenLoaiXe());
        return db.update("LoaiXe",values,"maLoaiXe=?",new String[]{String.valueOf(obj.getMaLoaiXe())});
    }
    public int delete(String id) {
        return db.delete("LoaiXe","maLoaiXe=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<LoaiXe> getData (String sql, String...selectionArgs) {

        List<LoaiXe> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()) {
            LoaiXe obj = new LoaiXe();
            obj.setMaLoaiXe(Integer.parseInt(c.getString(c.getColumnIndex("maLoaiXe"))));
            obj.setTenLoaiXe(c.getString(c.getColumnIndex("tenLoaiXe")));
            list.add(obj);
        }
        return list;
    }
    public List<LoaiXe> getAll() {
        String sql = "SELECT * FROM LoaiXe";
        return getData(sql);
    }
    public LoaiXe getID (String id) {
        String sql = "SELECT * FROM LoaiXe WHERE maLoaiXe=?";
        List<LoaiXe> list = getData(sql,id);
        return list.get(0);
    }
}
