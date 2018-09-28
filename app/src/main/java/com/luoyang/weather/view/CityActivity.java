package com.luoyang.weather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.luoyang.weather.R;
import com.luoyang.weather.adapter.CityListAdapter;
import com.luoyang.weather.modle.CityBean;
import com.luoyang.weather.modle.Event;
import com.luoyang.weather.view_modle.CityViewModle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 城市列表
 *
 * @author LuoYang 2018-9-28 16:40:44
 */
public class CityActivity extends AppCompatActivity {

    public static void goIn(Context context) {
        Intent intent = new Intent(context, CityActivity.class);
        context.startActivity(intent);
    }

    private CityListAdapter adapter;

    private RecyclerView cityRecyclerView;
    private TextView lodingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
        CityViewModle.getInstance().initCityData();
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
        lodingTextView = findViewById(R.id.textView2);

        cityRecyclerView = findViewById(R.id.recyclerView);
        cityRecyclerView.setVisibility(View.GONE);
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adapter = new CityListAdapter(new ArrayList<CityBean>());
        cityRecyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cityName_textView2:
                String code = (String) view.getTag();
                WeatherActivity.goIn(this, code);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventReceiver(Event event) {
        switch (event.getTag()) {
            case GET_CITY_DATA_SUCCESS:
                cityRecyclerView.setVisibility(View.VISIBLE);
                lodingTextView.setVisibility(View.GONE);
                ArrayList<CityBean> cityBeans = CityViewModle.getInstance().getCityBeans();
                adapter.getData().clear();
                adapter.getData().addAll(cityBeans);
                adapter.notifyDataSetChanged();
                break;
            case GET_CITY_DATA_ERROR:
                lodingTextView.setText(CityViewModle.getInstance().getErrorMsg());
                break;
        }
    }
}
