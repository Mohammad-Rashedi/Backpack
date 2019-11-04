package com.example.backpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    int splashTime = 1;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        throwThread();
    }

    private void throwThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {for(i = 0 ; i<=splashTime ; i++){
                    Thread.sleep(1000);}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intentGoToHome = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intentGoToHome);
            }
        }).start();
    }
}
