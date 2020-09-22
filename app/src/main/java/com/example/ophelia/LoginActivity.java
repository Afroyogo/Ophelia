package com.example.ophelia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.ophelia.UI.UIFunctions;
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
    }

    private void initialise() {
        autoLogin();
        UIFunctions.setFullScreen(this);
        fbAuth = FirebaseAuth.getInstance();
        Log.e("I", fbAuth.toString());
    }

    private void autoLogin() {
        user.setText("jaijaudenes@gmail.com");
        pwd.setText("123123");
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


}