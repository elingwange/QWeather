package elingwange.android.quantum.weather.widget;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.model.city.BaseCityModel;
import elingwange.android.quantum.weather.ui.MyCityActivity;

/**
 * Created by elingwange on 2014/10/15.
 */
@EViewGroup(R.layout.layout_mycity_item)
public class MyCityItem extends LinearLayout {

    @ViewById(R.id.front)
    TextView tvCity;

    private int position;
    private BaseCityModel model;

    public MyCityItem(Context context) {
        super(context);
    }

    public void bind(BaseCityModel model, int position) {
        this.position = position;
        this.model = model;
        tvCity.setText(model.getCityName());
    }

    @Click(R.id.delectCity)
    void deleteCityClick() {
        try {
            ((MyCityActivity) getContext()).delectCity(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
