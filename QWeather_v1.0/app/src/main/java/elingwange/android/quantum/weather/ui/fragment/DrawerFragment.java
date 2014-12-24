package elingwange.android.quantum.weather.ui.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import elingwange.android.quantum.weather.App;
import elingwange.android.quantum.weather.Const;
import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.model.SunModel;
import elingwange.android.quantum.weather.ui.CityActivity_;
import elingwange.android.quantum.weather.ui.MainActivity;
import elingwange.android.quantum.weather.ui.MyCityActivity_;
import elingwange.android.quantum.weather.ui.SettingActivity_;
import elingwange.android.quantum.weather.util.ActivityUtil;
import elingwange.android.quantum.weather.util.SharedPrefUtil;
import elingwange.android.quantum.weather.util.WeatherUtil;
import elingwange.android.quantum.weather.widget.RealtimeWeatherItem;

/**
 * Created by elingwange on 2014/11/10.
 */

@EFragment(R.layout.fragment_drawer)
public class DrawerFragment extends BaseFragment {

    ////////////////////////////////////////////
    //温馨提示
    @ViewById(R.id.drawer_weather_img)
    ImageView ivWeatherImg;

    @ViewById(R.id.drawer_weather)
    ImageView ivWeather;

    @ViewById(R.id.drawer_tv_weather)
    TextView tvWeather;

    @ViewById(R.id.drawer_temp)
    TextView tvTemp;

    @ViewById(R.id.drawer_chuanyi)
    TextView tvChuanyi;

    @ViewById(R.id.drawer_ganmao)
    TextView tvGanmao;

    @ViewById(R.id.drawer_ziwaixian)
    TextView tvZhiwaixian;

    @ViewById(R.id.drawer_yundong)
    TextView tvYundong;

    @ViewById(R.id.drawer_xiche)
    TextView tvXiche;

    @AfterViews
    void initFragment() {
    }

    /**
     * 更新温馨提示
     */
    public void updateReminder(SunModel model) {
        if (model != null) {
            App.mCurSunModel = model;
            ivWeatherImg.setImageResource(WeatherUtil.getImg(model.realtime.weather.info));
            ivWeather.setImageResource(WeatherUtil.getIcon(model.realtime.weather.info));
            tvWeather.setText(model.realtime.weather.info);
            tvTemp.setText(model.realtime.weather.temp + "℃");

            tvChuanyi.setText(model.life.info.chuanyi[0]);
            tvZhiwaixian.setText(model.life.info.ziwaixian[0]);
            tvYundong.setText(model.life.info.yundong[0]);
            tvGanmao.setText(model.life.info.ganmao[0]);
            tvXiche.setText(model.life.info.xiche[0]);
        }
    }

    private String getStr(String str[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            if (i == 0)
                sb.append(str[i]);
            else
                sb.append("\n" + str[i]);
        }
        return sb.toString();
    }

    @Click(R.id.drawer_add_city)
    void clickAddCity() {
        CityActivity_.intent(((MainActivity) getActivity()).homeFragment).startForResult(RealtimeWeatherItem.REQUEST_CODE);
        ((MainActivity) getActivity()).closeDrawer();
    }

    @Click(R.id.drawer_del_city)
    void clickDelCity() {
        MyCityActivity_.intent(((MainActivity) getActivity()).homeFragment).startForResult(RealtimeWeatherItem.REQUEST_CODE);
        ((MainActivity) getActivity()).closeDrawer();
    }

    @Click(R.id.drawwer_setting)
    void clickSetting() {
        openActivity(SettingActivity_.class);
        ((MainActivity) getActivity()).closeDrawer();
    }

    @Click(R.id.drawer_exit)
    void clickExit() {
        ((MainActivity) getActivity()).closeDrawer();

        if (SharedPrefUtil.getBoolean(Const.CONFIG_EXIT_KILL, false))
            ActivityUtil.finishKill();
        else
            ActivityUtil.finishAll();
    }
}