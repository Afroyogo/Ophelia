package com.example.ophelia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LoginActivity extends AppCompatActivity {

    private final String username = "Yai";
    private final String password = "123";
    private final Handler mHideHandler = new Handler();
    private Button btnConfirm;
    private EditText user, pwd;
    private Intent accessIntent;
    private CardView cvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        btnConfirm = findViewById(R.id.btConfirm);
        user = findViewById(R.id.etUsername);
        pwd = findViewById(R.id.etPassword);
        cvLogin = findViewById(R.id.cvLogin);

        accessIntent = new Intent(this, MainScreenActivity.class);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLogin()) {
                    startActivity(accessIntent);
                    finish();
                }
            }
        });

        setFullScreen();
    }


    private Boolean checkLogin() {
        if (user.getText().toString().equals(username) && pwd.getText().toString().equals(password)) {
            return true;
        } else {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            cvLogin.startAnimation(shake);
        }
        return false;
    }

//    Set fullscreen mode depending on the android system version
    private void setFullScreen() {
//        Checks device API
        if (Build.VERSION.SDK_INT < 19) {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                            Allows status bar to show dark icons if theme is light
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
    }
}