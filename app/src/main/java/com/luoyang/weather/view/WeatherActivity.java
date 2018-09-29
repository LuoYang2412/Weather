package com.luoyang.weather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.luoyang.weather.R;
import com.luoyang.weather.modle.Event;
import com.luoyang.weather.modle.WeatherBean;
import com.luoyang.weather.view_modle.WeatherViewModle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    private String code;
    private TextView lodingTextView;
    private TextView cityNameTextView;
    private TextView weatherTextView;
    private TextView minTempTextView;
    private TextView maxTempTextView;
    private Group weatherGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        code = getIntent().getStringExtra("code");
        WeatherViewModle.getInstance().initWeatherData(code);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        lodingTextView = findViewById(R.id.loading_textView3);
        cityNameTextView = findViewById(R.id.cityName_textView4);
        weatherTextView = findViewById(R.id.weather_textView3);
        minTempTextView = findViewById(R.id.minTemp_textView6);
        maxTempTextView = findViewById(R.id.maxTemp_textView8);
        weatherGroup = findViewById(R.id.weather_group);
        weatherGroup.setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventReceiver(Event event) {
        switch (event.getMessage()) {
            case GET_WEATHER_SUCCESS:
                setWeatherView();
                break;
            case GET_WEATHER_ERROR:
                lodingTextView.setText(WeatherViewModle.getInstance().getErrorMsg());
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void stickyEventReceiver(Event event) {
        switch (event.getMessage()) {
            case GET_WEATHER_SUCCESS_STICKY:
                setWeatherView();
                EventBus.getDefault().removeStickyEvent(event);
                break;
        }
    }

    private void setWeatherView() {
        WeatherBean weatherBean = WeatherViewModle.getInstance().getWeatherBean(code);
        if (weatherBean == null) {
            return;
        }
        cityNameTextView.setText(weatherBean.getCity());
        weatherTextView.setText(weatherBean.getWeather());
        minTempTextView.setText(weatherBean.getTempMin());
        maxTempTextView.setText(weatherBean.getTempMax());

        lodingTextView.setVisibility(View.GONE);
        weatherGroup.setVisibility(View.VISIBLE);
    }
}
