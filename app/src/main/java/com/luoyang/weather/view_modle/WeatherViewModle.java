package com.luoyang.weather.view_modle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.luoyang.weather.AppExecutors;
import com.luoyang.weather.modle.Event;
import com.luoyang.weather.modle.EventMessage;
import com.luoyang.weather.modle.WeatherBean;
import com.luoyang.weather.repo.Repository;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

public class WeatherViewModle {
    private static class WeatherViewModleHolder {
        private static final WeatherViewModle instance = new WeatherViewModle();
    }

    private WeatherViewModle() {
    }

    private Gson mGson = new Gson();
    private WeatherBean weatherBean;
    private HashMap<String, WeatherBean> weatherBeanHashMap = new HashMap<>();
    private String errorMsg;


    public static WeatherViewModle getInstance() {
        return WeatherViewModleHolder.instance;
    }

    /**
     * 天气数据
     */
    public void initWeatherData(final String code) {
        if (weatherBeanHashMap.get(code) != null) {//有缓存就不取库数据了
            EventBus.getDefault().postSticky(new Event(EventMessage.GET_WEATHER_SUCCESS_STICKY));//发送粘性事件，因为此时view还没注册EventBus
            return;
        }
        new AppExecutors().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String weatherData = Repository.getInstance().getWeatherData(code);
                try {
                    JsonObject jsonObject = mGson.fromJson(weatherData, JsonObject.class);
                    JsonObject weatherinfo = jsonObject.getAsJsonObject("weatherinfo");
                    weatherBean = mGson.fromJson(weatherinfo, WeatherBean.class);
                    weatherBeanHashMap.put(code, weatherBean);
                    EventBus.getDefault().post(new Event(EventMessage.GET_WEATHER_SUCCESS));
                } catch (JsonSyntaxException e) {
                    errorMsg = weatherData + "-----" + e.getMessage();
                    EventBus.getDefault().post(new Event(EventMessage.GET_WEATHER_ERROR));
                }
            }
        });
    }

    public WeatherBean getWeatherBean(String code) {
        WeatherBean weatherBean = weatherBeanHashMap.get(code);
        if (weatherBean == null) {
            initWeatherData(code);
        }
        return weatherBean;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
