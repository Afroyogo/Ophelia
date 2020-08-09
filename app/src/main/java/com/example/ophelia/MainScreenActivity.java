package com.example.ophelia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ophelia.UI.UIFunctions;

public class MainScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
        initialise();
    }

    private void initialise(){
        UIFunctions.setFullScreen(this);
    }
}