package com.example.backpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.backpack.myWeather.WeatherHomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
           }

    @OnClick(R.id.imageButtonBackpack)
    public void onBackpack(View view){
        Intent intent = new Intent(HomeActivity.this, BackpackListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.imageButtonWeather)
    public void onWeathet(View view){
        Intent intent = new Intent(HomeActivity.this, WeatherHomeActivity.class);
        startActivity(intent);
    }
    }

