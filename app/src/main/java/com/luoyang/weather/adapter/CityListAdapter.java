package com.luoyang.weather.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luoyang.weather.R;
import com.luoyang.weather.modle.CityBean;

import java.util.ArrayList;

public class CityListAdapter extends RecyclerView.Adapter<CityListViewHolder> {
    private ArrayList<CityBean> data = new ArrayList<>();

    public ArrayList<CityBean> getData() {
        return data;
    }

    public void setData(ArrayList<CityBean> data) {
        if (data != null && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
        }
    }

    @NonNull
    @Override
    public CityListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false);
        return new CityListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityListViewHolder cityListViewHolder, int i) {
        cityListViewHolder.cityName.setText(data.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class CityListViewHolder extends RecyclerView.ViewHolder {
    TextView cityName;

    CityListViewHolder(@NonNull View itemView) {
        super(itemView);
        cityName = itemView.findViewById(R.id.cityName_textView2);
    }
}