package fpoly.edu.carproject.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.edu.carproject.Adapter.DonHangAdapter;
import fpoly.edu.carproject.Adapter.NhanVienAdapterSpinner;
import fpoly.edu.carproject.Adapter.XeAdapterSpinnner;
import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.DonHangDAO;
import fpoly.edu.carproject.dao.NhanVienDAO;
import fpoly.edu.carproject.dao.XeDAO;
import fpoly.edu.carproject.model.DonHang;
import fpoly.edu.carproject.model.NhanVien;
import fpoly.edu.carproject.model.Xe;

public class DonHangFragment extends Fragment {
    ListView lvDonHang;
    ArrayList<DonHang> list;
    static DonHangDAO dao;
    DonHangAdapter adapter;
    DonHang item;

    FloatingActionButton fabDH;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Dialog dialog;
    EditText edMaDH;
    Spinner spnXe, spnNhanVien;
    TextView tvNgay,tvGia;
    Button btnHuy, btnThem;

    XeAdapterSpinnner xeAdapterSpinnner;
    ArrayList<Xe> listXe;
    XeDAO xeDAO;
    Xe xe;
    int tenXe;
    int gia;

    NhanVienAdapterSpinner nhanVienAdapterSpinner;
    ArrayList<NhanVien> listNhanVien;
    NhanVienDAO nhanVienDAO;
    NhanVien nhanVien;
    int tenNV;

    int positionTenXe, positionTenNV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_don_hang,null);
        lvDonHang = v.findViewById(R.id.lvDonHang);
        dao = new DonHangDAO(getActivity());
        fabDH = v.findViewById(R.id.fabDonHang);
        fabDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        updateLV();
        return v;
    }

    void updateLV () {
        list = (ArrayList<DonHang>) dao.getAll();
        adapter = new DonHangAdapter(getActivity(),list,this);
        lvDonHang.setAdapter(adapter);
    }
    public void del(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa đơn hàng");
        builder.setMessage("Bạn muốn xóa 1 đơn hàng");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Chắc chắn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(id);
                        updateLV();
                        dialog.cancel();
                        Toast.makeText(getContext(), "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        builder.setNegativeButton(
                "Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.don_hang_dialog);
        edMaDH = dialog.findViewById(R.id.edMaDH);
        spnNhanVien = dialog.findViewById(R.id.spnNhanVien);
        spnXe = dialog.findViewById(R.id.spnTenXeDH);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvGia = dialog.findViewById(R.id.tvGia);
        btnHuy = dialog.findViewById(R.id.btnHuyXeDH);
        btnThem = dialog.findViewById(R.id.btnThemXeDH);

        //set ngay
        tvNgay.setText("Ngày mua: "+sdf.format(new Date()));

        //set đơn hàng
        edMaDH.setEnabled(false);
        if (type != 0) {
            edMaDH.setText(String.valueOf(item.getMaDH()));
            listNhanVien = new ArrayList<>();
            for (int i = 0; i < listNhanVien.size();i++)
                if (item.getMaNV() == (listNhanVien.get(i).getMaNV())) {
                    positionTenNV = i;
                }
            spnNhanVien.setSelection(positionTenNV);
                listXe = new ArrayList<>();
            for (int i= 0; i<listXe.size();i++)
                if (item.getMaXe() == listXe.get(i).getMaLoaiXe()) {
                    positionTenXe = i;
                }
            spnXe.setSelection(positionTenXe);
//            tvNgay.setText("Ngày mua: "+item.getNgay());
//            tvGia.setText("Giá: "+item.getGia());
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new DonHang();
                item.setMaXe(tenXe);
                item.setMaNV(tenNV);
                item.setNgay(new Date());
                item.setGia(gia);
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //type =1
                    item.setMaDH(Integer.parseInt(edMaDH.getText().toString()));
                    if (dao.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                    updateLV();
                    dialog.dismiss();
                }

        });
        dialog.show();
        // custom spinner xe
        xeDAO = new XeDAO(context);
        listXe = new ArrayList<Xe>();
        listXe = (ArrayList<Xe>) xeDAO.getAll();
        xeAdapterSpinnner = new XeAdapterSpinnner(context,listXe);
        spnXe.setAdapter(xeAdapterSpinnner);
        spnXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenXe = listXe.get(position).getMaXe();
                gia = list.get(position).getGia();
                tvGia.setText("Giá: "+gia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // custom nhanvien
        nhanVienDAO = new NhanVienDAO(context);
        listNhanVien = new ArrayList<NhanVien>();
        listNhanVien = (ArrayList<NhanVien>) nhanVienDAO.getAll();
        nhanVienAdapterSpinner = new NhanVienAdapterSpinner(context,listNhanVien);
        spnNhanVien.setAdapter(nhanVienAdapterSpinner);
        spnNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenNV = listNhanVien.get(position).getMaNV();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}