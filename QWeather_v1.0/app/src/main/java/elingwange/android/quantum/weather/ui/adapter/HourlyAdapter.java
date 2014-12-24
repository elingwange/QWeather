package elingwange.android.quantum.weather.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import elingwange.android.quantum.weather.model.Weather;
import elingwange.android.quantum.weather.widget.HourlyItem;
import elingwange.android.quantum.weather.widget.HourlyItem_;

/**
 * Created by elingwange on 2014/12/10.
 */
@EBean
public class HourlyAdapter extends BaseAdapter {

    @RootContext
    Context mContext;

    List<Weather> hourlyWeather = new ArrayList<Weather>();

    public void appendToList(List<Weather> list) {
        if (list == null) {
            return;
        }
        hourlyWeather.clear();
        hourlyWeather.addAll(list);
        try {
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return hourlyWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return hourlyWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Weather model = hourlyWeather.get(position);
        HourlyItem item;

        if (convertView == null) {
            item = HourlyItem_.build(mContext);
        } else {
            item = (HourlyItem) convertView;
        }
        item.bind(model);
        return item;
    }

    public void clear() {
        hourlyWeather.clear();
        notifyDataSetChanged();
    }
}
