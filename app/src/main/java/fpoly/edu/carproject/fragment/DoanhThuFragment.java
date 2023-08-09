package fpoly.edu.carproject.fragment;

import android.app.DatePickerDialog;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.DonHangDAO;

public class DoanhThuFragment extends Fragment {
    Button btnTuNgay, btnDenNgay,btnDoanhThu;
    TextView tvTuNgay, tvDenNgay,tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mDay, mMonth, mYear;
    DonHangDAO donHangDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        tvTuNgay = v.findViewById(R.id.tvTuNgay);
        tvDenNgay = v.findViewById(R.id.tvDenNgay);
        btnTuNgay = v.findViewById(R.id.btnTuNgay);
        btnDenNgay = v.findViewById(R.id.btnDenNgay);
        btnDoanhThu = v.findViewById(R.id.btnDoanhThu);
        tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,mT,mYear,mMonth,mDay);
                dialog.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,mD,mYear,mMonth,mDay);
                dialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangDAO = new DonHangDAO(getActivity());
                String tuNgay = tvTuNgay.getText().toString();
                String denNgay = tvDenNgay.getText().toString();
                tvDoanhThu.setText("Doanh Thu: "+donHangDAO.getDoanhThu(tuNgay,denNgay) + " vnđ");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mT = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            tvTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mD = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            tvDenNgay.setText(sdf.format(c.getTime()));
        }
    };
//    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int dayOfMonth, int month, int year) {
//            mDay = dayOfMonth;
//            mMonth = month;
//            mYear = year;
//            GregorianCalendar c = new GregorianCalendar(mDay,mMonth,mYear);
//            tvTuNgay.setText(sdf.format(c.getTime()));
//        }
//    };
}