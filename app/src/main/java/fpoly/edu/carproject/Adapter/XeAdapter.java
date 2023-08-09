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
import fpoly.edu.carproject.dao.LoaiXeDAO;
import fpoly.edu.carproject.fragment.XeFragment;
import fpoly.edu.carproject.model.LoaiXe;
import fpoly.edu.carproject.model.Xe;

public class XeAdapter extends ArrayAdapter<Xe> {
    private Context context;
    private ArrayList<Xe> list;
    XeFragment fragment;
    TextView tvMaXe, tvTenXe,tvDongXe, tvNhienLieu, tvGiaBan,tvMaLoaiXe;
    ImageView imgShow, imgDel;
    LoaiXeDAO loaiXeDAO;
    public XeAdapter(@NonNull Context context,ArrayList<Xe> list,XeFragment fragment) {
        super(context,0,list);
        this.context =context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.xe_item,null);
        }
        final Xe item = list.get(position);
        if (item != null) {

            tvMaXe = v.findViewById(R.id.tvMaXe);
            tvMaXe.setText("Mã xe: "+item.getMaXe());

            tvTenXe = v.findViewById(R.id.tvTenXe);
            tvTenXe.setText("Tên xe: "+item.getTenXe());

            tvDongXe = v.findViewById(R.id.tvDongXe);
            tvDongXe.setText("Dòng xe: "+item.getDongXe());

            tvNhienLieu = v.findViewById(R.id.tvNhienLieu);
            tvNhienLieu.setText("Nhiên liệu: "+item.getNhienLieu());

            tvGiaBan = v.findViewById(R.id.tvGiaBan);
            tvGiaBan.setText("Giá bán: "+item.getGiaBan());

            loaiXeDAO = new LoaiXeDAO(context);
            LoaiXe loaiXe = loaiXeDAO.getID(String.valueOf(item.getMaLoaiXe()));
            tvMaLoaiXe = v.findViewById(R.id.tvMaLoaiXe);
            tvMaLoaiXe.setText("Loại: "+loaiXe.getTenLoaiXe());
            imgDel = v.findViewById(R.id.imgDel);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.del(String.valueOf(item.getMaXe()));
            }
        });

        return v;
    }

}
