package com.example.backpack.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.backpack.R;

public class SplashActivity extends AppCompatActivity {
    Intent intentGoToHome;
    int splashTime = 1;
    ActivityOptions options;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {for(i = 0 ; i<=splashTime ; i++){
                    Thread.sleep(1000);
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intentGoToHome = new Intent(SplashActivity.this, HomeActivity.class);

                    startActivity(intentGoToHome);
            }

        }).start();
    }


}
