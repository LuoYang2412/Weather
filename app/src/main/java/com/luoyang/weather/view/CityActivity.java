package com.luoyang.weather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luoyang.weather.R;

public class CityActivity extends AppCompatActivity {

    public static void goIn(Context context) {
        Intent intent = new Intent(context, CityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
    }
}
