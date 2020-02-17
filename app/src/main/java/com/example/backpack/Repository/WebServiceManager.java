package com.example.backpack.Repository;

import android.graphics.Bitmap;
import android.os.StrictMode;
import android.util.Log;

import com.example.backpack.WebService.WeatherGsonClasses.RetAPI;
import com.example.backpack.WebService.WeatherGsonClasses.WeatherFromWeb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceManager {

    WeatherFromWeb weatherFromWeb;
        OkHttpClient okHttpClient;
        Retrofit retrofit;
        RetAPI retAPI;
    public WebServiceManager() {
        initializeConnections();
    }

    private void initializeConnections() {

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //okHttp
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public WeatherFromWeb getWeatherFromWeb(String cityName){
        retAPI = retrofit.create(RetAPI.class);

        Call<WeatherFromWeb> callWeather = retAPI.getWeatherFromWeb(cityName,"a6f26861e57e144c594d8e86ca03edad");

        try {
            Response<WeatherFromWeb> execute = callWeather.execute();
             weatherFromWeb = execute.body();
             Log.i("==", "getWeatherFromWeb: "+weatherFromWeb.getName() + "temp " + weatherFromWeb.getMain().getTemp() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherFromWeb;
    }

}
