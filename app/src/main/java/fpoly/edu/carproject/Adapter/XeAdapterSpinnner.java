package fpoly.edu.carproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fpoly.edu.carproject.R;
import fpoly.edu.carproject.fragment.XeFragment;
import fpoly.edu.carproject.model.Xe;

public class XeAdapterSpinnner extends ArrayAdapter<Xe> {
    private Context context;
    private ArrayList<Xe> list;
    TextView tvXe;

    public XeAdapterSpinnner(@NonNull Context context, ArrayList<Xe> list) {
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
            v = inflater.inflate(R.layout.xe_spinnner,null);
        }
        final Xe item = list.get(position);
        if (item != null) {
            tvXe = v.findViewById(R.id.tvXeSpn);
            tvXe.setText("Tên xe: "+item.getDongXe());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.xe_spinnner,null);
        }
        final Xe item = list.get(position);
        if (item != null) {


            tvXe = v.findViewById(R.id.tvXeSpn);
            tvXe.setText("Tên xe: "+item.getDongXe());
        }
        return v;    }
}
