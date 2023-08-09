package fpoly.edu.carproject.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.edu.carproject.R;
import fpoly.edu.carproject.dao.ChuCuaHangDAO;
import fpoly.edu.carproject.model.ChuCuaHang;

public class ChangePassFragment extends Fragment {
    EditText edOldPass,  edPass, edComfirmPass;
    Button btnComfirm;
    ChuCuaHangDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edOldPass = v.findViewById(R.id.edOldPass);
        edPass = v.findViewById(R.id.edPass);
        edComfirmPass = v.findViewById(R.id.edComfirmPass);
        btnComfirm = v.findViewById(R.id.btnXacNhan);
        dao = new ChuCuaHangDAO(getActivity());
        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user = pref.getString("USER","");
                if (validate() > 0 ) {
                    dao = new ChuCuaHangDAO(getActivity());
                    ChuCuaHang chuCuaHang = new ChuCuaHang();
                    chuCuaHang = dao.getID(user);
                    chuCuaHang.setMatKhau(edPass.getText().toString());
                    if (dao.updatePass(chuCuaHang) > 0) {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edOldPass.setText("");
                        edComfirmPass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate() {
        int check =1;
        if (edOldPass.getText().length() == 0 || edPass.getText().length() == 0
                ||edComfirmPass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASS","");
            String pass =  edPass.getText().toString();
            String confirmPass = edComfirmPass.getText().toString();
            if (!passOld.equals(edOldPass.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ không khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(edPass.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu mới không trùng", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}