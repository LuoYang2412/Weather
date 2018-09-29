package com.luoyang.weather.modle;

import com.google.gson.annotations.SerializedName;

public class WeatherBean {
    /**
     * city : 赣榆
     * cityid : 101191003
     * temp1 : 14℃
     * temp2 : 19℃
     * weather : 小雨转小到中雨
     * img1 : n7.gif
     * img2 : d21.gif
     * ptime : 18:00
     */

    private String city;
    private String cityid;
    @SerializedName("temp1")
    private String tempMin;
    @SerializedName("temp2")
    private String tempMax;
    private String weather;
    private String img1;
    private String img2;
    private String ptime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
