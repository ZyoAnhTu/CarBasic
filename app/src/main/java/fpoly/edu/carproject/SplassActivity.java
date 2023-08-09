package fpoly.edu.carproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splass);

        ImageView ivSplass =findViewById(R.id.ivSplass);

        Glide.with(this).load(R.drawable.splass_activity).into(ivSplass);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplassActivity.this,Login.class));
            }
        },2000);
    }
}