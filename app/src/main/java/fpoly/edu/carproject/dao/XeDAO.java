package fpoly.edu.carproject.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.carproject.database.DbHelper;
import fpoly.edu.carproject.model.ChuCuaHang;
import fpoly.edu.carproject.model.Xe;

public class XeDAO {
    private SQLiteDatabase db;

    public XeDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public long insert (Xe obj) {
        ContentValues values = new ContentValues();
//        values.put("maXe",obj.getMaXe());
        values.put("tenXe",obj.getTenXe());
        values.put("dongXe",obj.getDongXe());
        values.put("nhienLieu",obj.getNhienLieu());
        values.put("giaBan",obj.getGiaBan());
        values.put("maLoaiXe",obj.getMaLoaiXe());
        return db.insert("Xe",null,values);
    }
    public int update(Xe obj) {
        ContentValues values = new ContentValues();
        values.put("tenXe",obj.getTenXe());
        values.put("dongXe",obj.getDongXe());
        values.put("nhienLieu",obj.getNhienLieu());
        values.put("giaBan",obj.getGiaBan());
        values.put("maLoaiXe",obj.getMaLoaiXe());
        return db.update("Xe",values,"maXe=?",new String[]{String.valueOf(obj.getMaXe())});
    }
    public int delete(String id) {
        return db.delete("Xe","maXe=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<Xe> getData (String sql, String...selectionArgs) {

        List<Xe> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()) {
            Xe obj = new Xe();
            obj.setMaXe(Integer.parseInt(c.getString(c.getColumnIndex("maXe"))));
            obj.setTenXe(c.getString(c.getColumnIndex("tenXe")));
            obj.setDongXe(c.getString(c.getColumnIndex("dongXe")));
            obj.setNhienLieu(c.getString(c.getColumnIndex("nhienLieu")));
            obj.setGiaBan(Integer.parseInt(c.getString(c.getColumnIndex("giaBan"))));
            obj.setMaLoaiXe(Integer.parseInt(c.getString(c.getColumnIndex("maLoaiXe"))));
            list.add(obj);
        }
        return list;
    }
    public List<Xe> getAll() {
        String sql = "SELECT * FROM Xe";
        return getData(sql);
    }
    public Xe getID (String id) {
        String sql = "SELECT * FROM Xe WHERE maXe=?";
        List<Xe> list = getData(sql,id);
        return list.get(0);
    }
}
