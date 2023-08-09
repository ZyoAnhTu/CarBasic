package fpoly.edu.carproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.carproject.database.DbHelper;
import fpoly.edu.carproject.model.ChuCuaHang;
import fpoly.edu.carproject.model.LoaiXe;
import fpoly.edu.carproject.model.NhanVien;

public class demoDB {
    private SQLiteDatabase db;
    NhanVienDAO nhanVienDAO;
    static final String TAG ="//======";

    public demoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        nhanVienDAO = new NhanVienDAO(context);
    }
    public void nhanVien() {
        List<NhanVien> list = new ArrayList<>();
        NhanVien nhanVien = new NhanVien(1,"Nguyễn Văn A","2000",123456);
        if (nhanVienDAO.insert(nhanVien) > 0) {
            Log.i(TAG,"Thêm nhân viên thành công ");
        }else{
            Log.i(TAG,"Thêm nhân viên thất bại ");
        }
        Log.i(TAG,"tổng"+nhanVienDAO.getAll().size());
        nhanVien = new NhanVien(2,"Nguyễn Văn B","2000",123456);
        nhanVienDAO.update(nhanVien);
        Log.i(TAG,"tổng"+nhanVienDAO.getAll().size());
        nhanVienDAO.delete("1");
        Log.i(TAG,"tổng"+nhanVienDAO.getAll().size());
    }
}
