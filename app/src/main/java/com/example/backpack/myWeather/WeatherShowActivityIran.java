package com.example.backpack.myWeather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backpack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherShowActivityIran extends AppCompatActivity {
    @BindView(R.id.editTextSearchWSIran)
    EditText editTextSearchWSIran;
    @BindView(R.id.textViewCityNameWSIran)
    TextView textViewCityNameWSIran;
    @BindView(R.id.textViewTemperatureWSIran)
    TextView textViewTemperatureWSIran;
    @BindView(R.id.imageViewWeatherConditionWSIran)
    ImageView imageViewWeatherConditionWSIran;
    @BindView(R.id.imageViewTemperatureConditionWSIran)
    ImageView imageViewTemperatureConditionWSIran;
    @BindView(R.id.listViewWSIran)
    ListView listViewWSIran;
    List<String> cityNames;
    List<String> cityNamesPersian;
    List<String> cityNamesPersianSolid;
    ArrayAdapter arrayAdapter;
    URL url;
    JSONObject jObject = null;
    StringBuilder builder;
    int positionSelected;
    String searchText;
    NetworkInfo info;
    NetworkInfo[] infoAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_show_iran);
        ButterKnife.bind(this);
        initializeCityNames();
        //For Network Security Configuration:
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cityNamesPersian);
        listViewWSIran.setAdapter(arrayAdapter);
        listViewWSIran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                            String cityNameSelected = cityNames.get(position);
                            if (checkConnection()){getWeather myGetWeather;
                                myGetWeather = new getWeather();
                                myGetWeather.execute(cityNameSelected);}
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"به اینترنت دسترسی ندارید",Toast.LENGTH_LONG).show();

                        }
                    }
                }).start();
            }
        });
    }

    private Boolean checkConnection() {
        Boolean b = false;
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
                b = true; }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @OnClick(R.id.buttonSearchWSIran)
    public void onButtonSearchWSIran(View view){
        Boolean found = false;
        searchText = String.valueOf(editTextSearchWSIran.getText());
        for (int i = 0 ;i< cityNamesPersian.size();i++){
            if(searchText.equalsIgnoreCase(cityNamesPersian.get(i))|
                    searchText.equalsIgnoreCase(cityNames.get(i))|
                    searchText.equalsIgnoreCase(cityNamesPersianSolid.get(i))){
                    String name = cityNamesPersian.get(i);
                    Log.i("==r", name);
                    Toast.makeText(this, name + " در لیست موجود است", Toast.LENGTH_LONG).show();
                found = true;
                break;
            }
        }
        if (!found){
            Toast.makeText(this, "شهر مورد نظر پیدا نشد", Toast.LENGTH_LONG).show();
        }
    }

    private void initializeCityNames() {
        cityNames = new ArrayList<>();
        cityNamesPersian = new ArrayList<>();
        cityNamesPersianSolid = new ArrayList<>();
        cityNames.add("Arak, IR");
        cityNamesPersian.add("اراک");
        cityNames.add("Ardabil, IR");
        cityNamesPersian.add("اردبیل");
        cityNames.add("Urmia, IR");
        cityNamesPersian.add("ارومیه");
        cityNames.add("Isfahan, IR");
        cityNamesPersian.add("اصفهان");
        cityNames.add("Ahvaz, IR");
        cityNamesPersian.add("اهواز");
        cityNames.add("Ilam, IR");
        cityNamesPersian.add("ایلام");
        cityNames.add("Bojnurd, IR");
        cityNamesPersian.add("بجنورد");
        cityNames.add("Bandar Bushehr, IR");
        cityNamesPersian.add("بندر بوشهر");
        cityNames.add("Bandar Abbas, IR");
        cityNamesPersian.add("بندرعباس");
        cityNames.add("Birjand, IR");
        cityNamesPersian.add("بیرجند");
        cityNames.add("Tabriz, IR");
        cityNamesPersian.add("تبریز");
        cityNames.add("Tehran, IR");
        cityNamesPersian.add("تهران");
        cityNames.add("Khoram Abad, IR");
        cityNamesPersian.add("خرم آباد");
        cityNames.add("Rasht, IR");
        cityNamesPersian.add("رشت");
        cityNames.add("Zahedan, IR");
        cityNamesPersian.add("زاهدان");
        cityNames.add("Zanjan, IR");
        cityNamesPersian.add("زنجان");
        cityNames.add("Sari, IR");
        cityNamesPersian.add("ساری ");
        cityNames.add("Semnan, IR");
        cityNamesPersian.add("سمنان");
        cityNames.add("Sanandaj, IR");
        cityNamesPersian.add("سنندج");
        cityNames.add("Shiraz, IR");
        cityNamesPersian.add("شیراز");
        cityNames.add("Qazvin, IR");
        cityNamesPersian.add("قزوین");
        cityNames.add("Qom, IR");
        cityNamesPersian.add("قم");
        cityNames.add("Karaj, IR");
        cityNamesPersian.add("کرج");
        cityNames.add("Kerman, IR");
        cityNamesPersian.add("کرمان");
        cityNames.add("Kermanshah, IR");
        cityNamesPersian.add("کرمانشاه");
        cityNames.add("Gorgan, IR");
        cityNamesPersian.add("گرگان");
        cityNames.add("Mashhad, IR");
        cityNamesPersian.add("مشهد");
        cityNames.add("Hamedan, IR");
        cityNamesPersian.add("همدان ");
        cityNames.add("Yasuj, IR");
        cityNamesPersian.add("یاسوج");
        cityNames.add("Yazd, IR");
        cityNamesPersian.add("یزد");
        for (int i=0 ; i <cityNamesPersian.size();i++){
            String[] split = cityNames.get(i).split(",");
            cityNamesPersianSolid.add(split[0]) ;
        }
    }

    public class getWeather extends AsyncTask<String , Void , JSONObject> {
        String cityName;
        String name;
        String temperature;
        String weather;
        @Override
        protected JSONObject doInBackground(String... strings) {
            cityName = strings[0];
            try {
                url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+ cityName +"&appid=a6f26861e57e144c594d8e86ca03edad");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection==null){
                    Toast.makeText(getApplicationContext(),"مشکلی پیش آمده",Toast.LENGTH_LONG).show();
                }
                //get info from site
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                if (inputStream==null){
                    Toast.makeText(getApplicationContext(),"مشکلی پیش آمده",Toast.LENGTH_LONG).show();
                }
                builder = new StringBuilder();
                builder.append(bufferedReader.readLine());
                jObject = new JSONObject(String.valueOf(builder));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            JSONArray weatherJSONArray = null;
            JSONObject weatherJSONObject = null;
            JSONObject mainJSONObject = null;
            try {
                try {
                    name = jsonObject.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"مشکلی پیش آمده",Toast.LENGTH_LONG).show();
                }
                weatherJSONArray = jsonObject.getJSONArray("weather");
                weatherJSONObject = weatherJSONArray.getJSONObject(0);
                weather = weatherJSONObject.getString("description");
                mainJSONObject = jsonObject.getJSONObject("main");
                temperature = mainJSONObject.getString("temp");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"مشکلی پیش آمده",Toast.LENGTH_LONG).show();
            }
            name = cityNamesPersian.get(positionSelected);
            textViewCityNameWSIran.setText( name);
            double temperatureInt =  Double.parseDouble(temperature);
            temperatureInt = (int) (temperatureInt - 273.15);
            textViewTemperatureWSIran.setText("" + temperatureInt);
            switch (weather){
                case "light rain":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.lightrain);
                    break;
                case "fog":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.fog);
                    break;
                case "few clouds":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.brokenclouds);
                    break;
                case "scattered clouds":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.brokenclouds);
                    break;
                case "clear sky":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.clearsky);
                    break;
                case "light intensity shower rain":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.lightintensityshowerrain);
                    break;
                case "thunderstorm with rain":
                    imageViewWeatherConditionWSIran.setImageResource(R.drawable.thunderstormwithrain);
                    break;
            }
            if(temperatureInt>25){
                imageViewTemperatureConditionWSIran.setImageResource(R.drawable.temperaturehot);
            }else if(temperatureInt<15){
                imageViewTemperatureConditionWSIran.setImageResource(R.drawable.temperaturecold);
            }else if (temperatureInt>15 && temperatureInt<25){
                imageViewTemperatureConditionWSIran.setImageResource(R.drawable.temperaturemild);
            }
        }
    }}