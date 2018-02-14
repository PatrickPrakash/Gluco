package com.projectx.gluco.MainActivities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projectx.gluco.Authentication.MainAuthActivity;
import com.projectx.gluco.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this,MainAuthActivity.class);
                 startActivity(homeIntent);
                 finish();
            }
        },SPLASH_TIME_OUT);
    }

}
