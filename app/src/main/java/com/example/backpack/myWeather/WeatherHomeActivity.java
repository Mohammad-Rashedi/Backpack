package com.example.backpack.myWeather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.backpack.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherHomeActivity extends AppCompatActivity {
    NetworkInfo info;
    NetworkInfo[] infoAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageButtonIranWHome)
    public void onImageButtonIranWHome(View view){
        Intent intentIran = new Intent(WeatherHomeActivity.this, WeatherShowActivityIran.class);
        getConnection(intentIran);
    }

    @OnClick(R.id.imageButtonWorldWHome)
    public void onImageButtonWorldWHome(View view){
        Intent intentWorld = new Intent(WeatherHomeActivity.this, WeatherShowActivityWorldActivity.class);
        getConnection(intentWorld);
    }

    private void getConnection(Intent intent){
        try {        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            if (connectivityManager==null){
                Toast.makeText(this,"به اینترنت دسترسی ندارید",Toast.LENGTH_LONG).show();
            }else{        info = connectivityManager.getActiveNetworkInfo();
            }
            if (info == null ){
                Toast.makeText(this,"به اینترنت دسترسی ندارید",Toast.LENGTH_LONG).show();
            } else if (  !(info.isConnected())|  !(info.isAvailable())) {
                Toast.makeText(this,"به اینترنت دسترسی ندارید",Toast.LENGTH_LONG).show();
            }else {
                startActivity(intent);}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

