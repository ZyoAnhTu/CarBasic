package fpoly.edu.carproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.edu.carproject.R;
import fpoly.edu.carproject.model.NhanVien;

public class NhanVienAdapterSpinner extends ArrayAdapter<NhanVien> {
    private Context context;
    private ArrayList<NhanVien> list;
    TextView tvMaNV, tvTenNV;
    public NhanVienAdapterSpinner(@NonNull Context context, ArrayList<NhanVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nhanvien_item_spinner,null);
        }
        final NhanVien item = list.get(position);
        if (item != null){
            tvMaNV = v.findViewById(R.id.tvMaNVSpn);
            tvMaNV.setText(item.getMaNV()+". ");

            tvTenNV = v.findViewById(R.id.tvTenNVSpn);
            tvTenNV.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nhanvien_item_spinner,null);
        }
        final NhanVien item = list.get(position);
        if (item != null){
            tvMaNV = v.findViewById(R.id.tvMaNVSpn);
            tvMaNV.setText(item.getMaNV()+". ");

            tvTenNV = v.findViewById(R.id.tvTenNVSpn);
            tvTenNV.setText(item.getHoTen());
        }
        return v;    }
}
