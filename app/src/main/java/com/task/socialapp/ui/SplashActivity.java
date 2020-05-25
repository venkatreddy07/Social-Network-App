package com.task.socialapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.task.socialapp.MyApplication;
import com.task.socialapp.R;
import com.task.socialapp.ui.auth.LoginActivity;
import com.task.socialapp.ui.home.HomeActivity;
import com.task.socialapp.util.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectToScreen();
            }
        }, 2000);

    }

    private void redirectToScreen() {
        if (MyApplication.sharedPreferences.getLoginDetails(Constants.Pref.LOGGED_IN, false)) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }
}
