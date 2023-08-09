package fpoly.edu.carproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.edu.carproject.Adapter.TopAdapter;
import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.DonHangDAO;
import fpoly.edu.carproject.model.Top;

public class TopFragment extends Fragment {
    ListView lvTop;
    ArrayList<Top> list;
    TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lvTop = v.findViewById(R.id.lvTop);
        DonHangDAO donHangDAO = new DonHangDAO(getActivity());
        list = (ArrayList<Top>) donHangDAO.getTop();
        adapter = new TopAdapter(getActivity(),list,this);
        lvTop.setAdapter(adapter);
        return v;
    }
}