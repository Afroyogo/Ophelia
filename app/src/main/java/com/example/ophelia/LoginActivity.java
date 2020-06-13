package com.example.ophelia;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    private final String username = "Yai";
    private final String password = "123";

    private Button btnConfirm;
    private EditText user, pwd;
    private Intent accessIntent;
    private CardView cvLogin;

    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mContentView = findViewById(R.id.fullscreen_content);
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
        hide();
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

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mHideHandler.postDelayed(mHidePart2Runnable, 0);
    }
}