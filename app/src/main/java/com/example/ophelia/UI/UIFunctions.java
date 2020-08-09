package com.example.ophelia.UI;

import android.os.Build;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UIFunctions {

    //    Set fullscreen mode depending on the android system version
    public static void setFullScreen(AppCompatActivity callingActivity) {
//        Checks device API
        if (Build.VERSION.SDK_INT < 19) {
            callingActivity.getWindow().getDecorView()
                    .setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = callingActivity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                            Allows status bar to show dark icons if theme is light
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        }
        ActionBar actionBar = callingActivity.getSupportActionBar();
        actionBar.hide();
    }
}
