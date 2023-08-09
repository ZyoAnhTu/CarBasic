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
import fpoly.edu.carproject.fragment.LoaiXeFragment;
import fpoly.edu.carproject.model.LoaiXe;

public class LoaiXeAdapter extends ArrayAdapter<LoaiXe> {
    private Context context;
    private ArrayList<LoaiXe> list;
    LoaiXeFragment fragment;
    TextView tvMaLX, tvTenLX;
    ImageView btnDel;

    public LoaiXeAdapter(@NonNull Context context, LoaiXeFragment fragment, ArrayList<LoaiXe> list) {
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
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaixe_item, null);
        }
        final LoaiXe item = list.get(position);
        if (item != null) {
            tvMaLX = v.findViewById(R.id.tvMaLX);
            tvMaLX.setText("Mã loại xe: "+item.getMaLoaiXe());

            tvTenLX = v.findViewById(R.id.tvTenLX);
            tvTenLX.setText("Tên loại xe: "+item.getTenLoaiXe());

            btnDel = v.findViewById(R.id.imDelLX);
        }
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.del(String.valueOf(item.getMaLoaiXe()));
            }
        });
        return v;
    }
    protected void openDialog(final Context context,final int type){

    }
}
