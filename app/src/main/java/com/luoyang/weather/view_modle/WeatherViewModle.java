package com.luoyang.weather.view_modle;

import com.luoyang.weather.AppExecutors;
import com.luoyang.weather.repo.Repository;

import java.net.MalformedURLException;
import java.net.URL;

public class WeatherViewModle {
    private static class WeatherViewModleHolder {
        private static final WeatherViewModle instance = new WeatherViewModle();
    }

    private WeatherViewModle() {
    }


    public static WeatherViewModle getInstance() {
        return WeatherViewModleHolder.instance;
    }

    /**
     * 初始化天气数据
     */
    public void initWeatherData(final String code) {
        new AppExecutors().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String weatherData = Repository.getInstance().getWeatherData(code);
            }
        });
    }
}
