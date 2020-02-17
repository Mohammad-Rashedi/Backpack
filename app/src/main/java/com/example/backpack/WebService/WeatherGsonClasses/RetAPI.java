package com.example.backpack.WebService.WeatherGsonClasses;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetAPI {

    @GET("data/2.5/weather")
    Call<WeatherFromWeb> getWeatherFromWeb(@Query("q") String cityName,
                                           @Query("appid") String appid);


}
//Call<WeatherFromWeb> getWeatherFromWeb();
//data/2.5/weather?q=tehran&appid=a6f26861e57e144c594d8e86ca03edad