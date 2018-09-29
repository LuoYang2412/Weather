package com.luoyang.weather.repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 数据仓库类
 *
 * @author LuoYang
 */
public class Repository {
    private static class RepositoryHoler {
        private static final Repository instance = new Repository();
    }

    private Repository() {
    }

    public static Repository getInstance() {
        return RepositoryHoler.instance;
    }

    private String dataString;

    /**
     * 初始化城市数据
     *
     * @return
     */
    public String getCityData() {
        InputStream resourceAsStream = getClass().getResourceAsStream("/assets/city.json");
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder dataStringBuilder = new StringBuilder();
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                dataStringBuilder.append(line);
            }
            dataString = dataStringBuilder.toString();
        } catch (IOException e) {
            dataString = e.getMessage();
        }
        return dataString;
    }

    public String getWeatherData(String code) {
        String urlStr = "http://www.weather.com.cn/data/cityinfo/" + code + ".html";
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
