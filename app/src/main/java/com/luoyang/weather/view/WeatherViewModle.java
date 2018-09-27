package com.luoyang.weather.view;

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
    public void initWeatherData() {

    }
}
