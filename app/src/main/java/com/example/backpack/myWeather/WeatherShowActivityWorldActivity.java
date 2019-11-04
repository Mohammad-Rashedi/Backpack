package com.example.backpack.myWeather;

import androidx.appcompat.app.AppCompatActivity;

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

public class WeatherShowActivityWorldActivity extends AppCompatActivity {
    @BindView(R.id.editTextSearchWSWorld)
    EditText editTextSearchWSWorld;
    @BindView(R.id.textViewCityNameWSWorld)
    TextView textViewCityNameWSWorld;
    @BindView(R.id.textViewTemperatureWSWorld)
    TextView textViewTemperatureWSWorld;
    @BindView(R.id.imageViewWeatherConditionWSWorld)
    ImageView imageViewWeatherConditionWSWorld;
    @BindView(R.id.imageViewTemperatureConditionWSWorld)
    ImageView imageViewTemperatureConditionWSWorld;
    @BindView(R.id.listViewWSWorld)
    ListView listViewWSWorld;
    List<String> cityNamesWorld;
    List<String> cityNamesPersianWorld;
    List<String> cityNamesWorldSolid;
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
        setContentView(R.layout.activity_weather_show_world);
        ButterKnife.bind(this);
        initializeCityNames();
        //For Network Security Configuration:
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cityNamesPersianWorld);
        listViewWSWorld.setAdapter(arrayAdapter);
        listViewWSWorld.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                            String cityNameSelected = cityNamesWorld.get(position);
                            if (checkConnection()){
                                getWeather myGetWeather;
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
            }else{
                info = connectivityManager.getActiveNetworkInfo();
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

    @OnClick(R.id.buttonSearchWSWorld)
    public void onButtonSearchWSWorld(View view){
        searchText = String.valueOf(editTextSearchWSWorld.getText());
        Boolean found = false;
        for (int i = 0 ;i< cityNamesWorld.size();i++){
            if(searchText.equalsIgnoreCase(cityNamesWorld.get(i))|
                    searchText.equalsIgnoreCase(cityNamesPersianWorld.get(i))|
                    searchText.equalsIgnoreCase(cityNamesWorldSolid.get(i))){
                String name = cityNamesPersianWorld.get(i);
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
        cityNamesWorld = new ArrayList<>();
        cityNamesPersianWorld = new ArrayList<>();
        cityNamesWorldSolid = new ArrayList<>();
        cityNamesWorld.add("Athens, GR");
        cityNamesPersianWorld.add("آتن");
        cityNamesWorld.add("Astana, KZ");
        cityNamesPersianWorld.add("آستانه");
        cityNamesWorld.add("Amsterdam, NL");
        cityNamesPersianWorld.add("آمستردام");
        cityNamesWorld.add("Ankara, TR");
        cityNamesPersianWorld.add("آنکارا");
        cityNamesWorld.add("Abu Dhabi, AE");
        cityNamesPersianWorld.add("ابوظبی");
        cityNamesWorld.add("Ottawa, CA");
        cityNamesPersianWorld.add("اتاوا");
        cityNamesWorld.add("Stockholm, SE");
        cityNamesPersianWorld.add("استکهلم");
        cityNamesWorld.add("Islamabad, PK");
        cityNamesPersianWorld.add("اسلام آباد");
        cityNamesWorld.add("Oslo, NO");
        cityNamesPersianWorld.add("اسلو");
        cityNamesWorld.add("Yerevan, AM");
        cityNamesPersianWorld.add("ایروان");
        cityNamesWorld.add("Baku, AZ");
        cityNamesPersianWorld.add("باکو");
        cityNamesWorld.add("Bangkok, TH");
        cityNamesPersianWorld.add("بانکوک");
        cityNamesWorld.add("Bucharest, RO");
        cityNamesPersianWorld.add("بخارست");
        cityNamesWorld.add("Brasilia, BR");
        cityNamesPersianWorld.add("برازیلیا");
        cityNamesWorld.add("Berlin, DE");
        cityNamesPersianWorld.add("برلین");
        cityNamesWorld.add("Brussels, BE");
        cityNamesPersianWorld.add("بروکسل");
        cityNamesWorld.add("Baghdad, IQ");
        cityNamesPersianWorld.add("بغداد");
        cityNamesWorld.add("Buenos Aires, AR");
        cityNamesPersianWorld.add("بوئنوس آیرس");
        cityNamesWorld.add("Budapest, HU");
        cityNamesPersianWorld.add("بوداپست");
        cityNamesWorld.add("Beirut, LB");
        cityNamesPersianWorld.add("بیروت");
        cityNamesWorld.add("Paris, FR");
        cityNamesPersianWorld.add("پاریس");
        cityNamesWorld.add("Prague, CZ");
        cityNamesPersianWorld.add("پراگ");
        cityNamesWorld.add("Beijing, CN");
        cityNamesPersianWorld.add("پکن");
        cityNamesWorld.add("Tashkent, UZ");
        cityNamesPersianWorld.add("تاشکند");
        cityNamesWorld.add("Taipei, TW");
        cityNamesPersianWorld.add("تایپه");
        cityNamesWorld.add("Tbilisi, GE");
        cityNamesPersianWorld.add("تفلیس");
        cityNamesWorld.add("Tokyo, JP");
        cityNamesPersianWorld.add("توکیو");
        cityNamesWorld.add("Jakarta, ID");
        cityNamesPersianWorld.add("جاکارتا");
        cityNamesWorld.add("Damascus, SY");
        cityNamesPersianWorld.add("دمشق");
        cityNamesWorld.add("Doha, QA");
        cityNamesPersianWorld.add("دوحه");
        cityNamesWorld.add("Dushanbe, TJ");
        cityNamesPersianWorld.add("دوشنبه");
        cityNamesWorld.add("New Delhi, IN");
        cityNamesPersianWorld.add("دهلی نو");
        cityNamesWorld.add("Rome, IT");
        cityNamesPersianWorld.add("رم");
        cityNamesWorld.add("Riyadh, SA");
        cityNamesPersianWorld.add("ریاض");
        cityNamesWorld.add("Seoul, KR");
        cityNamesPersianWorld.add("سئول");
        cityNamesWorld.add("Sanaa, YE");
        cityNamesPersianWorld.add("صنعا");
        cityNamesWorld.add("Ashgabat, TM");
        cityNamesPersianWorld.add("عشق آباد");
        cityNamesWorld.add("Cairo, EG");
        cityNamesPersianWorld.add("قاهره");
        cityNamesWorld.add("Kabul, AF");
        cityNamesPersianWorld.add("کابل");
        cityNamesWorld.add("Sydney, AU");
        cityNamesPersianWorld.add("سیدنی");
        cityNamesWorld.add("Copenhagen, DK");
        cityNamesPersianWorld.add("کپنهاگ");
        cityNamesWorld.add("Kuala Lumpur, MY");
        cityNamesPersianWorld.add("کوالالامپور");
        cityNamesWorld.add("London, GB");
        cityNamesPersianWorld.add("لندن");
        cityNamesWorld.add("Lisbon, PT");
        cityNamesPersianWorld.add("لیسبون");
        cityNamesWorld.add("Madrid, ES");
        cityNamesPersianWorld.add("مادرید");
        cityNamesWorld.add("Washington DC., US");
        cityNamesPersianWorld.add("واشینگتن");
        cityNamesWorld.add("Warsaw, PL");
        cityNamesPersianWorld.add("ورشو");
        cityNamesWorld.add("Vienna, AT");
        cityNamesPersianWorld.add("وین");
        for (int i=0 ; i <cityNamesPersianWorld.size();i++){
          String[] split = cityNamesWorld.get(i).split(",");
            cityNamesWorldSolid.add(split[0]) ;
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
            name = cityNamesPersianWorld.get(positionSelected);
            textViewCityNameWSWorld.setText( name);
            double temperatureInt =  Double.parseDouble(temperature);
            temperatureInt = (int) (temperatureInt - 273.15);
            textViewTemperatureWSWorld.setText("" + temperatureInt);
            switch (weather){
                case "light rain":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.lightrain);
                    break;
                case "fog":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.fog);
                    break;
                    case "few clouds":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.brokenclouds);
                    break;
                case "scattered clouds":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.brokenclouds);
                    break;
                case "clear sky":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.clearsky);
                    break;
                case "light intensity shower rain":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.lightintensityshowerrain);
                    break;
                case "thunderstorm with rain":
                    imageViewWeatherConditionWSWorld.setImageResource(R.drawable.thunderstormwithrain);
                    break;
            }
            if(temperatureInt>25){
                imageViewTemperatureConditionWSWorld.setImageResource(R.drawable.temperaturehot);
            }else if(temperatureInt<15){
                imageViewTemperatureConditionWSWorld.setImageResource(R.drawable.temperaturecold);
            }else if (temperatureInt>15 && temperatureInt<25){
                imageViewTemperatureConditionWSWorld.setImageResource(R.drawable.temperaturemild);
            }
        }
    }}

