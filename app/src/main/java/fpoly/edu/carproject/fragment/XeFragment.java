package fpoly.edu.carproject.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.edu.carproject.Adapter.LoaiXeAdapterSpinner;
import fpoly.edu.carproject.Adapter.XeAdapter;
import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.LoaiXeDAO;
import fpoly.edu.carproject.dao.XeDAO;
import fpoly.edu.carproject.model.LoaiXe;
import fpoly.edu.carproject.model.Xe;


public class XeFragment extends Fragment {
    ListView lvXe;
    ArrayList<Xe> list;
    XeDAO dao;
    XeAdapter adapter;
    Xe item;

    FloatingActionButton fabXe;
    Dialog dialog;
    EditText edTenXe, edDongXe, edNhienLieu,edGiaBan,edMaXe;
    Button btnHuy, btnThem;
    ImageView imgAddImg,imgXeDialog;
    Spinner spnMaLoaiXe;

    LoaiXeAdapterSpinner loaiXeAdapterSpinner;
    ArrayList<LoaiXe> listLoaiXe;
    LoaiXeDAO loaiXeDAO;
    LoaiXe loaiXe;
    int maXe;
     int positionTenNV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_xe, container, false);
        lvXe = v.findViewById(R.id.lvXe);
        dao = new XeDAO(getActivity());
        updateLV();
        fabXe = v.findViewById(R.id.fabXe);
        fabXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvXe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<Xe>) dao.getAll();
        adapter = new XeAdapter(getActivity(),list,this);
        lvXe.setAdapter(adapter);
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
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.xe_dialog);

        edMaXe = dialog.findViewById(R.id.edMaXeDialog);
        edTenXe = dialog.findViewById(R.id.edTenXeDialog);
        edDongXe = dialog.findViewById(R.id.edDongXeDialog);
        edNhienLieu = dialog.findViewById(R.id.edNhienLieuDialog);
        edGiaBan = dialog.findViewById(R.id.edGiaBanDialog);
        btnHuy = dialog.findViewById(R.id.btnHuyXe);
        btnThem = dialog.findViewById(R.id.btnThemXe);
        imgAddImg = dialog.findViewById(R.id.imgAddImgXeDialog);
        imgXeDialog = dialog.findViewById(R.id.imgXeDialog);
        spnMaLoaiXe = dialog.findViewById(R.id.spnMaLoaiXe);
        edMaXe.setEnabled(false);



        if (type != 0 ) {
            edMaXe.setText(String.valueOf(item.getMaXe()));
            edTenXe.setText(item.getTenXe());
            edDongXe.setText(item.getDongXe());
            edNhienLieu.setText(item.getNhienLieu());
            edGiaBan.setText(Integer.toString(item.getGiaBan()));
            listLoaiXe = new ArrayList<>();
//            for (int i = 0; i<listLoaiXe.size();i++)
//                if (item.getMaLoaiXe() == (listLoaiXe.get(i).getMaLoaiXe())) {
//                    positionTenNV = i;
//                }
//            spnMaLoaiXe.setSelection(positionTenNV);

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
                item= new Xe();
                item.setTenXe(edTenXe.getText().toString());
                item.setDongXe(edDongXe.getText().toString());
                item.setNhienLieu(edNhienLieu.getText().toString());
                try{
                    item.setGiaBan(Integer.parseInt(edGiaBan.getText().toString()));
                }catch (Exception e) {
                }
                item.setMaLoaiXe(maXe);
                dao = new XeDAO(context);
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) >= 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaXe(Integer.parseInt(edMaXe.getText().toString().toUpperCase()));
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
        //custom spinner loại xe
        loaiXeDAO = new LoaiXeDAO(context);
        listLoaiXe = new ArrayList<LoaiXe>();
        listLoaiXe = (ArrayList<LoaiXe>) loaiXeDAO.getAll();
        loaiXeAdapterSpinner = new LoaiXeAdapterSpinner(context,listLoaiXe);
        spnMaLoaiXe.setAdapter(loaiXeAdapterSpinner);
        spnMaLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maXe =listLoaiXe.get(position).getMaLoaiXe();
//                Toast.makeText(context, "Chọn: "+listLoaiXe.get(position).getTenLoaiXe(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialog.show();
    }
    public int validate() {
        int check = 1;
        if (edTenXe.getText().length() == 0 || edDongXe.getText().length() == 0
        || edNhienLieu.getText().length() == 0|| edGiaBan.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin xe", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check = 1;
    }
}