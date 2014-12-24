package elingwange.android.quantum.weather.widget;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.model.city.BaseCityModel;

/**
 * Created by elingwange on 2014/10/15.
 */

@EViewGroup(R.layout.layout_city_item)
public class CityItem extends LinearLayout {

    @ViewById(R.id.tv_city_name_layout)
    TextView tvCityName;

    public CityItem(Context context) {
        super(context);
    }

    public void bind(BaseCityModel model) {
        tvCityName.setText(model.getCityName());
    }
}
