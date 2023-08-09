package fpoly.edu.carproject.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.edu.carproject.database.DbHelper;
import fpoly.edu.carproject.model.DonHang;
import fpoly.edu.carproject.model.Top;
import fpoly.edu.carproject.model.Xe;

public class DonHangDAO {

    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DonHangDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert (DonHang obj) {
        ContentValues values = new ContentValues();
        values.put("maXe",obj.getMaXe());
        values.put("maNV",obj.getMaNV());
        values.put("Ngay",sdf.format(obj.getNgay()));
        values.put("gia",obj.getGia());
        values.put("maCCH",obj.getMaCCH());
        return db.insert("DonHang",null,values);
    }
    public int update(DonHang obj) {
        ContentValues values = new ContentValues();
        values.put("maXe",obj.getMaXe());
        values.put("maNV",obj.getMaNV());
        values.put("ngay",sdf.format(obj.getNgay()));
        values.put("gia",obj.getGia());
        values.put("maCCH",obj.getMaCCH());
        return db.update("DonHang",values,"maDH=?",new String[]{String.valueOf(obj.getMaDH())});
    }
    public int delete(String id) {
        return db.delete("DonHang","maDH=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<DonHang> getData (String sql, String...selectionArgs) {

        List<DonHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()) {
            DonHang obj = new DonHang();
            obj.setMaDH(Integer.parseInt(c.getString(c.getColumnIndex("maDH"))));
            obj.setMaXe(Integer.parseInt(c.getString(c.getColumnIndex("maXe"))));
            obj.setMaNV(Integer.parseInt(c.getString(c.getColumnIndex("maNV"))));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            } catch (ParseException e) {
            }
            obj.setGia(Integer.parseInt(c.getString(c.getColumnIndex("gia"))));
            obj.setMaCCH(c.getString(c.getColumnIndex("maCCH")));
            list.add(obj);
        }
        return list;
    }
    public List<DonHang> getAll() {
        String sql = "SELECT * FROM DonHang";
        return getData(sql);
    }
    public DonHang getID (String id) {
        String sql = "SELECT * FROM DonHang WHERE maDH=?";
        List<DonHang> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<Top> getTop () {
        String sqlTop = "SELECT maXe, count(maXe) as soLuong FROM DonHang GROUP BY maXe ORDER BY soLuong DESC LIMIT 5";
        List<Top> list = new ArrayList<Top>();
        XeDAO xeDAO = new XeDAO(context);
        Cursor c = db.rawQuery(sqlTop,null);

        while (c.moveToNext()) {
            Top top = new Top();
            Xe xe = xeDAO.getID(c.getString(c.getColumnIndex("maXe")));
            top.setXe(xe.getTenXe());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM (gia) as doanhThu FROM DonHang WHERE ngay BETWEEN ? AND ?";
        List<Integer>list =new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});

        while (c.moveToNext()) {
            try {
                    list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
