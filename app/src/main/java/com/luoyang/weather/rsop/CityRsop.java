package com.luoyang.weather.rsop;

import android.support.annotation.Keep;

import com.luoyang.weather.AppExecutors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileReader fileReader = new FileReader("file:///android_asset/city.json");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String tempString = "";
                    while ((tempString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(tempString);
                    }
                    bufferedReader.close();
                    String cityDataString = stringBuilder.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return "";
    }
}
