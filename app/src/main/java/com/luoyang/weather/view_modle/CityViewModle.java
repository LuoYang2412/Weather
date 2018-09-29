package com.luoyang.weather.view_modle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.luoyang.weather.AppExecutors;
import com.luoyang.weather.modle.CityBean;
import com.luoyang.weather.modle.Event;
import com.luoyang.weather.modle.EventMessage;
import com.luoyang.weather.repo.Repository;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    private String errorMsg = "";
    private ArrayList<CityBean> cityBeans = new ArrayList<>();

    /**
     * 初始化城市数据
     */
    public void initCityData() {
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                String data = Repository.getInstance().getCityData();
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    setGsonBuilder(gsonBuilder);

                    ArrayList<CityBean> tempCityBeans = gsonBuilder.create().fromJson(data, new TypeToken<ArrayList<CityBean>>() {
                    }.getType());
                    cityBeans.clear();
                    cityBeans.addAll(tempCityBeans);
                    EventBus.getDefault().post(new Event(EventMessage.GET_CITY_DATA_SUCCESS));
                } catch (JsonSyntaxException e) {
                    errorMsg = data + "----" + e.getMessage();
                    EventBus.getDefault().post(new Event(EventMessage.GET_CITY_DATA_ERROR));
                }
            }

            private void setGsonBuilder(GsonBuilder gsonBuilder) {
                //=======GSON高级用法===========//
                gsonBuilder.registerTypeAdapter(new TypeToken<ArrayList<CityBean>>() {
                }.getType(), new JsonDeserializer<ArrayList<CityBean>>() {
                    ArrayList<CityBean> list = new ArrayList<>();
                    Gson mGson = new Gson();

                    @Override
                    public ArrayList<CityBean> deserialize(JsonElement json, Type typeOfT,
                                                           JsonDeserializationContext context) throws JsonParseException {
                        if (json.isJsonObject()) {
                            JsonElement zone = json.getAsJsonObject().get("zone");
                            if (zone == null) {//没有下属，是具体城市
                                list.add(mGson.fromJson(json, CityBean.class));
                            } else {//有下属，是行政区域。省，市，县
                                deserialize(zone, typeOfT, context);
                            }
                        } else {
                            JsonArray jsonArray = json.getAsJsonArray();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                deserialize(jsonArray.get(i), typeOfT, context);
                            }
                        }
                        return list;
                    }
                });
                //=======GSON高级用法===========//
            }
        });
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ArrayList<CityBean> getCityBeans() {
        return cityBeans;
    }
}
