package com.luoyang.weather;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.luoyang.weather.view.CityActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CityActivity.goIn(StartActivity.this);
                finish();
                overridePendingTransition(R.anim.weather_fade_in, R.anim.weather_fade_out);
            }
        }, 1000);
    }
}
