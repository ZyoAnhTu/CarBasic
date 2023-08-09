package fpoly.edu.carproject.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.LoaiXeDAO;
import fpoly.edu.carproject.dao.NhanVienDAO;
import fpoly.edu.carproject.dao.XeDAO;
import fpoly.edu.carproject.fragment.DonHangFragment;
import fpoly.edu.carproject.model.DonHang;
import fpoly.edu.carproject.model.LoaiXe;
import fpoly.edu.carproject.model.NhanVien;
import fpoly.edu.carproject.model.Xe;

public class DonHangAdapter extends ArrayAdapter<DonHang> {
    private Context context;
    private ArrayList<DonHang> list;
    DonHangFragment fragment;
    TextView tvMaDH, tvMaXe, tvTenXe,tvTenNV,tvGiaMua, tvNgayMua;
    ImageView imgDel;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    NhanVienDAO nhanVienDAO;
    XeDAO xeDAO;

    public DonHangAdapter(@NonNull Context context, ArrayList<DonHang> list, DonHangFragment fragment) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.don_hang_item,null);
        }
        final DonHang item = list.get(position);
        if (item != null) {
            tvMaDH = v.findViewById(R.id.tvMaDH);
            tvMaDH.setText("Đơn hàng: "+item.getMaDH());

            xeDAO = new XeDAO(context);
            Xe xe = xeDAO.getID(String.valueOf(item.getMaXe()));
            tvTenXe = v.findViewById(R.id.tvTenXeDH);
            tvTenXe.setText("Tên xe: "+xe.getTenXe());

            nhanVienDAO = new NhanVienDAO(context);
            NhanVien nhanVien = nhanVienDAO.getID(String.valueOf(item.getMaNV()));
            tvTenNV = v.findViewById(R.id.tvTenNVDH);
            tvTenNV.setText("Nhân viên: "+nhanVien.getHoTen());

            tvNgayMua = v.findViewById(R.id.tvNgayMua);
            if (item.getNgay()!= null)
            tvNgayMua.setText("Ngày mua: "+sdf.format(item.getNgay()));


            tvGiaMua = v.findViewById(R.id.tvGiaBanDH);
            tvGiaMua.setText("Giá: "+item.getGia());

            imgDel = v.findViewById(R.id.imgDelDH);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.del(String.valueOf(item.getMaDH()));
            }
        });
        return v;
    }
}
