package fpoly.edu.carproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.edu.carproject.dao.ChuCuaHangDAO;

public class Login extends AppCompatActivity {
    EditText edUser ,edPass;
    Button btnLogin, btnReset;
    CheckBox chkRememberLogin;
    String strUser ,strPass;
    ChuCuaHangDAO chuCuaHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUser = findViewById(R.id.edtUser);
        edPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnReset = findViewById(R.id.btnReset);
        chkRememberLogin = findViewById(R.id.chkRememberPass);
        chuCuaHangDAO = new ChuCuaHangDAO(this);
        SharedPreferences pref =getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USER","");
        String pass = pref.getString("PASS","");
        Boolean rem = pref.getBoolean("REMEMBER",false);

        edUser.setText(user);
        edPass.setText(pass);
        chkRememberLogin.setChecked(rem);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edPass.setText("");
            }
        });
    }
    public void checkLogin() {
        strUser = edUser.getText().toString();
        strPass = edPass.getText().toString();  
        if (strPass.isEmpty() || strUser.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
        }else {
            if (chuCuaHangDAO.checkLogin(strUser,strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                rememberLogin(strUser,strPass,chkRememberLogin.isChecked());
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberLogin(String u,String p,boolean stt) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!stt) {
            editor.clear();
        }else {
            editor.putString("USER",u);
            editor.putString("PASS",p);
            editor.putBoolean("REMEMBER",stt);
        }
        editor.commit();
    }
}