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
import fpoly.edu.carproject.model.LoaiXe;

public class LoaiXeAdapterSpinner extends ArrayAdapter<LoaiXe> {
    private Context context;
    private ArrayList<LoaiXe> list;
    TextView tvMaLoaiXe, tvTenLoaiXe;
    public LoaiXeAdapterSpinner(@NonNull Context context, ArrayList<LoaiXe> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==  null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loaixe_item_spinner,null);
        }
        final LoaiXe item = list.get(position);
        if (item != null) {
            tvMaLoaiXe = v.findViewById(R.id.tvMaLoaiXeSpn);
            tvMaLoaiXe.setText(item.getMaLoaiXe()+". ");

            tvTenLoaiXe = v.findViewById(R.id.tvTenLoaiXeSpn);
            tvTenLoaiXe.setText(item.getTenLoaiXe());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v ==  null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loaixe_item_spinner,null);
        }
        final LoaiXe item = list.get(position);
        if (item != null) {
            tvMaLoaiXe = v.findViewById(R.id.tvMaLoaiXeSpn);
            tvMaLoaiXe.setText(item.getMaLoaiXe()+". ");

            tvTenLoaiXe = v.findViewById(R.id.tvTenLoaiXeSpn);
            tvTenLoaiXe.setText(" "+item.getTenLoaiXe());
        }
        return v;
    }
}
