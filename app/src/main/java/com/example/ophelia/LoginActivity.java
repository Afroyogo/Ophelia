package com.example.ophelia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private final String username = "Yai";
    private final String password = "123";

    private Button btnConfirm;
    private EditText user, pwd;
    private Intent accessIntent;
    private CardView cvLogin;

    private FirebaseAuth fbAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        btnConfirm = findViewById(R.id.btConfirm);
        user = findViewById(R.id.etUsername);
        pwd = findViewById(R.id.etPassword);
        cvLogin = findViewById(R.id.cvLogin);

        initialise();

        accessIntent = new Intent(this, MainScreenActivity.class);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        setFullScreen();
    }

    private void initialise() {
        fbAuth = FirebaseAuth.getInstance();
        Log.e("I", fbAuth.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        currentUser = fbAuth.getCurrentUser();
    }

    private void login() {
        fbAuth.signInWithEmailAndPassword(user.getText().toString(), pwd.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("I", "signInWithEmail:success");
                            FirebaseUser user = fbAuth.getCurrentUser();
                            startActivity(accessIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("I", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
    }

    private void register() {
        fbAuth.createUserWithEmailAndPassword(user.getText().toString(), pwd.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("I", "createUserWithEmail:success");
                            FirebaseUser user = fbAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("I", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, getString(R.string.AUTHENTICATION_FAILED),
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}