package fpoly.edu.carproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.edu.carproject.R;
import fpoly.edu.carproject.fragment.NhanVienFragment;
import fpoly.edu.carproject.model.NhanVien;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    private Context context;
    NhanVienFragment fragment;
    private ArrayList<NhanVien> list;
    TextView tvMaNV, tvTenNV, tvNamSinh, tvSoDT;
    ImageView imDel;
    public NhanVienAdapter(@NonNull Context context, NhanVienFragment fragment, ArrayList<NhanVien> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nhanvien_item,null);
        }
        final NhanVien item = list.get(position);
        if (item != null) {
            tvMaNV = v.findViewById(R.id.maNV);
            tvMaNV.setText("Mã nhân viên: "+item.getMaNV());

            tvTenNV = v.findViewById(R.id.tenNV);
            tvTenNV.setText("Tên nhân viên: "+item.getHoTen());

            tvNamSinh = v.findViewById(R.id.namSinh);
            tvNamSinh.setText("Năm sinh: "+item.getNamSinh());

            tvSoDT = v.findViewById(R.id.soDT);
            tvSoDT.setText("Số điện thoại: "+item.getSoDT());

            imDel = v.findViewById(R.id.imDelNV);
        }
        imDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.del(String.valueOf(item.getMaNV()));
            }
        });

        return v;
    }
}
