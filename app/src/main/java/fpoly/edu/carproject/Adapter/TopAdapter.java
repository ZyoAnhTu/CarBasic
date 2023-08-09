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
import fpoly.edu.carproject.fragment.TopFragment;
import fpoly.edu.carproject.model.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    ArrayList<Top> list;
    TopFragment fragment;
    TextView tvTopXe, tvSoLuongTop;

    public TopAdapter(@NonNull Context context, ArrayList<Top> list, TopFragment fragment) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = list.get(position);
        if (item != null) {
            tvTopXe = v.findViewById(R.id.tvXeTop);
            tvTopXe.setText("Tên xe: "+item.getXe());

            tvSoLuongTop = v.findViewById(R.id.tvSoLuongTop);
            tvSoLuongTop.setText("Số lượng: "+item.getSoLuong());
        }
        return v;
    }
}
