package com.luoyang.weather.rsop;

import android.support.annotation.Keep;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Keep
public class CityRsop {
    private static class CityRsopHoler {
        private static final CityRsop instance = new CityRsop();
    }

    private CityRsop() {
    }

    public static CityRsop getInstance() {
        return CityRsopHoler.instance;
    }

    public String getData() {
        
        return "";
    }
}
