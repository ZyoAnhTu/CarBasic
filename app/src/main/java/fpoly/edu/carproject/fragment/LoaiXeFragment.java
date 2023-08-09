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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.edu.carproject.Adapter.LoaiXeAdapter;
import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.LoaiXeDAO;
import fpoly.edu.carproject.model.LoaiXe;
import fpoly.edu.carproject.model.NhanVien;

public class LoaiXeFragment extends Fragment {
    ListView lvLoaiXe;
    ArrayList<LoaiXe> list;
    static LoaiXeDAO dao;
    LoaiXeAdapter adapter;
    LoaiXe item;

    FloatingActionButton fabLX;
    Dialog dialog;
    EditText edMaLoaiXe, edTenLoaiXe;
    Button btnHuy, btnThem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_xe, container, false);
        lvLoaiXe = v.findViewById(R.id.lvLoaiXe);
        dao = new LoaiXeDAO(getActivity());
        fabLX = v.findViewById(R.id.fabLoaiXe);
        updateLV();
        fabLX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvLoaiXe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    void updateLV () {
        list = (ArrayList<LoaiXe>) dao.getAll();
        adapter = new LoaiXeAdapter(getActivity(),this,list);
        lvLoaiXe.setAdapter(adapter);
    }
    public void del (String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa loại xe");
        builder.setMessage("Bạn chắc chắc muốn xóa");
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
    protected void openDialog (final Context context, final int type) {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.loaixe_dialog);
        edMaLoaiXe = dialog.findViewById(R.id.edMaLX);
        edTenLoaiXe = dialog.findViewById(R.id.edTenLX);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnThem = dialog.findViewById(R.id.btnThem);
        edMaLoaiXe.setEnabled(false);
        if (type !=0) {
            edMaLoaiXe.setText(Integer.toString(item.getMaLoaiXe()));
            edTenLoaiXe.setText(item.getTenLoaiXe());
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
                item = new LoaiXe();
//                item.setMaLoaiXe(Integer.parseInt(edMaLoaiXe.getText().toString()));
                item.setTenLoaiXe(edTenLoaiXe.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaLoaiXe(Integer.parseInt(edMaLoaiXe.getText().toString().toUpperCase()));
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
    public int validate () {
        int check;
        if ( edTenLoaiXe.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin của nhân viên", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            check = 1;
        }
        return check;
    }
}