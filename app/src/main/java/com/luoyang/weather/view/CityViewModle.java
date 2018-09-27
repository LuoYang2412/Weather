package com.luoyang.weather.view;

/**
 * 城市ViewModle类，提供城市数据
 */
public class CityViewModle {
    private static class CityViewModleHolder {
        private static final CityViewModle instance = new CityViewModle();
    }

    private CityViewModle() {
    }

    public static CityViewModle getInstance() {
        return CityViewModleHolder.instance;
    }

    /**
     * 初始化城市数据
     */
    public void initCityData() {

    }
}
