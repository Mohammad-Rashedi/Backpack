package com.example.backpack.View.Weather;

import android.widget.ImageView;
import android.widget.TextView;

public class WeatherItem {
    String cityName;
    String cityTemperature;
    String cityWeatherCondition;

    public String getCityWeatherCondition() {
        return cityWeatherCondition;
    }

    public void setCityWeatherCondition(String cityWeatherCondition) {
        this.cityWeatherCondition = cityWeatherCondition;
    }

    public WeatherItem(String cityName, String cityTemperature,String cityWeatherCondition) {
        this.cityName = cityName;
        this.cityTemperature = cityTemperature;
        this.cityWeatherCondition = cityWeatherCondition;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityTemperature() {
        return cityTemperature;
    }

    public void setCityTemperature(String cityTemperature) {
        this.cityTemperature = cityTemperature;
    }
}
