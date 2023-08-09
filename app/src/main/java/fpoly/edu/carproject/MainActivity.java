package fpoly.edu.carproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import fpoly.edu.carproject.dao.ChuCuaHangDAO;
import fpoly.edu.carproject.fragment.ChangePassFragment;
import fpoly.edu.carproject.fragment.DoanhThuFragment;
import fpoly.edu.carproject.fragment.DonHangFragment;
import fpoly.edu.carproject.fragment.LoaiXeFragment;
import fpoly.edu.carproject.fragment.NhanVienFragment;
import fpoly.edu.carproject.fragment.TopFragment;
import fpoly.edu.carproject.fragment.XeFragment;
import fpoly.edu.carproject.model.ChuCuaHang;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderViewer;
    TextView edUser;
    ChuCuaHangDAO chuCuaHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);

        toolbar = findViewById(R.id.toolbar1);

        setActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        NavigationView nv = findViewById(R.id.nvView);
        mHeaderViewer = nv.getHeaderView(0);
        edUser = mHeaderViewer.findViewById(R.id.txtUser);


//        Intent i = getIntent();
//        String user = i.getStringExtra("USER");
//        chuCuaHangDAO = new ChuCuaHangDAO(this);
//        ChuCuaHang chuCuaHang = chuCuaHangDAO.getID(String.valueOf(this));
//        String username = chuCuaHang.getHoTen();
//        edUser.setText("Xin chào: "+ username);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_NhanVien:
                        setTitle("Quản lý Nhân Viên");
                        NhanVienFragment nhanVienFragment = new NhanVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,nhanVienFragment)
                                .commit();
                        break;
                    case R.id.nav_LoaiXe:
                        setTitle("Quản lý Loại Xe");
                        LoaiXeFragment loaiXeFragment = new LoaiXeFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,loaiXeFragment)
                                .commit();
                        break;
                    case R.id.nav_Xe:
                        setTitle("Quản lý Xe");
                        XeFragment xeFragment = new XeFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,xeFragment)
                                .commit();
                        break;
                    case R.id.nav_DonHang:
                        setTitle("Quản lý Đơn Hàng");
                        DonHangFragment donHangFragment = new DonHangFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,donHangFragment)
                                .commit();
                        break;
                    case R.id.sub_Top:
                        setTitle("Top xe hãng xe bán chạy");
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,topFragment)
                                .commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Doanh Thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doanhThuFragment)
                                .commit();
                        break;
                    case R.id.sub_Pass:
                        setTitle("Đổi mật khẩu");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,changePassFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Thông báo");
                        alertDialog.setMessage("Chắc Chắn thoát?");

                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Thoát", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                finish();
                            }
                        });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alertDialog.show();
                        break;
                }
                drawer.closeDrawers();;

                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }
}