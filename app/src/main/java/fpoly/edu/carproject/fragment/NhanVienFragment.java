package fpoly.edu.carproject.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.edu.carproject.Adapter.NhanVienAdapter;
import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.NhanVienDAO;
import fpoly.edu.carproject.model.NhanVien;

public class NhanVienFragment extends Fragment {
    ListView lvNhanVien;
    ArrayList<NhanVien> list;
    static NhanVienDAO dao;
    NhanVienAdapter adapter;
    NhanVien item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaNV,edTenNV,edNamSinh,edSoDT;
    Button btnHuy, btnLuu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        lvNhanVien = v.findViewById(R.id.lvNhanVien);
        fab = v.findViewById(R.id.floatingActionButton2);
//        fab.setBackgroundColor(Color.RGBToHSV(220,208,255););
        dao = new NhanVienDAO(getActivity());
        updateLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    void updateLV() {
        list = (ArrayList<NhanVien>) dao.getAll();
        adapter = new NhanVienAdapter(getActivity(),this,list);
        lvNhanVien.setAdapter(adapter);
    }
    public void del (final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa nhân viên");
        builder.setMessage("Bạn chắc chắc muốn xóa ?");
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
    dialog.setContentView(R.layout.nhan_vien_dialog);
    edMaNV = dialog.findViewById(R.id.edMaNV);
    edTenNV = dialog.findViewById(R.id.edTenNV);
    edNamSinh = dialog.findViewById(R.id.edNamSinh);
    edSoDT = dialog.findViewById(R.id.edSoDT);
    btnHuy = dialog.findViewById(R.id.btnHuy);
    btnLuu = dialog.findViewById(R.id.btnThem);
    edMaNV.setEnabled(false);
    if (type != 0) {
        edMaNV.setText(String.valueOf(item.getMaNV()));
        edTenNV.setText(item.getHoTen());
        edNamSinh.setText(item.getNamSinh());
        edSoDT.setText(String.valueOf(item.getSoDT()));
    }
    btnHuy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    btnLuu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            item = new NhanVien();
            item.setHoTen(edTenNV.getText().toString());
            item.setNamSinh(edNamSinh.getText().toString());

            try {item.setSoDT(Integer.parseInt(edSoDT.getText().toString()));
            }catch (Exception e) {}

            if (validate() > 0) {
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    try {}catch (Exception e) {}
                    item.setMaNV(Integer.parseInt(edMaNV.getText().toString().toUpperCase()));
                    if (dao.update(item) > 0 ) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                updateLV();
                dialog.dismiss();
            }
        }
    });
    dialog.show();
    }
    public int validate() {
        int check ;
        if (edTenNV.getText().length()==0 || edNamSinh.getText().length() == 0|| edSoDT.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin của nhân viên", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            check = 1;
        }
        return check;
    }
}