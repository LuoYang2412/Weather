package com.luoyang.weather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.luoyang.weather.R;
import com.luoyang.weather.view_modle.WeatherViewModle;

/**
 * 天气详情页
 *
 * @author LuoYang 2018-9-28 16:53:48
 */
public class WeatherActivity extends AppCompatActivity {
    /**
     * @param code 城市编码
     */
    public static void goIn(Context context, String code) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        WeatherViewModle.getInstance().initWeatherData(getIntent().getStringExtra("code"));
    }

    private void initView() {
        textView = findViewById(R.id.textView3);
    }
}
