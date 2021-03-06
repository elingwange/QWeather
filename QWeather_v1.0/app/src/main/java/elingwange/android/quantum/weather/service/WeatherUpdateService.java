package elingwange.android.quantum.weather.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.UiThread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import elingwange.android.quantum.weather.App;
import elingwange.android.quantum.weather.Const;
import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.dao.SunDataHelper;
import elingwange.android.quantum.weather.data.GsonRequest;
import elingwange.android.quantum.weather.data.RequestManager;
import elingwange.android.quantum.weather.model.SunModel;
import elingwange.android.quantum.weather.receiver.WeatherWidget;
import elingwange.android.quantum.weather.ui.MainActivity_;
import elingwange.android.quantum.weather.util.DateUtil;
import elingwange.android.quantum.weather.util.LogUtil;
import elingwange.android.quantum.weather.util.SharedPrefUtil;
import elingwange.android.quantum.weather.util.WeatherUtil;

/**
 * Created by elingwange on 2014/12/10.
 */

@EService
public class WeatherUpdateService extends Service {

    /** Action = elingwange.android.quantum.weather.action.update_weather */
    public final static String ACTION_UPDATE_WEATHER = "elingwange.android.quantum.weather.action.UPDATE_WEATHER";
    public final static String ACTION_SWITH_CITY = "elingwange.android.quantum.weather.action.SWITH_CITY";
    public final static String ACTION_NEXT_CITY = "elingwange.android.quantum.weather.action.NEXT_CITY";
    public final static String ACTION_TIME_SET = "android.intent.action.TIME_SET";

    protected final static String TAG = "WeatherUpdateService";

    protected final static int UPDATE_DELAY = 1;
    protected final static int UPDATE_PERIOD = 3600 * 1000;

    private SunDataHelper mDB;

    private SunModel mSunModel;

    private RemoteViews remoteView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mDB = new SunDataHelper(this);

        remoteView = new RemoteViews(getPackageName(), R.layout.widget_4x2);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getWeatherFormNet(getNextWeatherCode(App.getCurCityIndex()));
            }
        }, UPDATE_DELAY, UPDATE_PERIOD);

    }

    private String getNextWeatherCode(int index) {
        return App.getMyArea().get(index % App.getMyArea().size()).getWeatherCode();
    }

    private void getWeather(String id) {
         SunModel sunModel = mDB.query(id);
        if (mSunModel == null) {
            getWeatherFormNet(id);
        } else {
            mSunModel = sunModel;
            sendUpdateWeather();;
        }
    }

    private void getWeatherFormNet(String id) {
        executeRequest(new GsonRequest(Const.WEATHER_INFO_URL + "?app=tq360&_jsonp=renderData&code=" + id, SunModel.SunRequestData.class, responseListener(), errorListener()));
    }

    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    /**
     * 获取数据成功回调
     * @return
     */
    private Response.Listener<SunModel.SunRequestData> responseListener() {

        return new Response.Listener<SunModel.SunRequestData>() {
            @Override
            public void onResponse(SunModel.SunRequestData sunRequestData) {
                mSunModel = sunRequestData.data;
                sendUpdateWeather();
            }
        };
    }

    /**
     * 获取数据错误回调
     * @return
     */
    protected Response.ErrorListener errorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        };
    }

    // 发送广播，更新天气
    private void sendUpdateWeather() {
        sendBroadcast(new Intent(ACTION_UPDATE_WEATHER));
    }

    @UiThread
    void updateTime() {

        Date date = new Date();
        // 定义SimpleDateFormat对象
        remoteView.setTextViewText(R.id.tv_provider_time, DateUtil.getCurTime());
        remoteView.setTextViewText(R.id.tv_provider_week, DateUtil.getCurWeek());
        remoteView.setOnClickPendingIntent(R.id.rl_provider_widget, PendingIntent.getActivity(this, 0, new Intent(this, MainActivity_.class), 0));
        remoteView.setOnClickPendingIntent(R.id.ll_provider_right, PendingIntent.getBroadcast(this, 1, new Intent(ACTION_NEXT_CITY), 0));
        ComponentName componentName = new ComponentName(getApplication(), WeatherWidget.class);
        AppWidgetManager.getInstance(getApplication()).updateAppWidget(componentName, remoteView);
    }

    @UiThread
    void updateWeather() {

        if (mSunModel != null) {
            if (mSunModel.realtime.cityName != null)
                remoteView.setTextViewText(R.id.tv_provider_city, mSunModel.realtime.cityName);
            remoteView.setTextViewText(R.id.tv_provider_temp, mSunModel.realtime.weather.temp + "℃");
            remoteView.setTextViewText(R.id.tv_provider_weather, mSunModel.realtime.weather.info);
            remoteView.setImageViewResource(R.id.iv_provider_weather, WeatherUtil.getIcon(mSunModel.realtime.weather.info));
        }

        ComponentName componentName = new ComponentName(getApplication(), WeatherWidget.class);
        AppWidgetManager.getInstance(getApplication()).updateAppWidget(componentName, remoteView);
    }

    // 广播接收者去接收系统每分钟的提示广播，来更新时间
    private BroadcastReceiver mTimePickerBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_UPDATE_WEATHER)) {
                updateWeather();
            } else if (intent.getAction().equals(ACTION_SWITH_CITY)) {
                LogUtil.i(TAG, "swith city " + App.getMyArea().get(App.getCurCityIndex()).getCityName());
                getWeather(getNextWeatherCode(App.getCurCityIndex()));
            } else if (intent.getAction().equals(ACTION_NEXT_CITY)) {
                LogUtil.i(TAG, "next city");
                App.setCurCityIndex((App.getCurCityIndex() + 1) % App.getMyArea().size());
                getWeather(getNextWeatherCode(App.getCurCityIndex()));
            } else {
                updateTime();
            }
        }
    };

    // 注册事件，注（只能在代码中注册）
    private void registerReceiver() {
        IntentFilter updateIntent = new IntentFilter();
        updateIntent.addAction(ACTION_UPDATE_WEATHER);
        updateIntent.addAction(ACTION_SWITH_CITY);
        updateIntent.addAction(ACTION_NEXT_CITY);
        updateIntent.addAction(ACTION_TIME_SET);
        updateIntent.addAction(Intent.ACTION_TIME_TICK);
        updateIntent.addAction(Intent.ACTION_DATE_CHANGED);
        updateIntent.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        registerReceiver(mTimePickerBroadcast, updateIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        registerReceiver();
        updateTime();
        return super.onStartCommand(intent, START_STICKY, startId);
    }

    @Override
    public void onDestroy() {
        // 注销广播
        if (mTimePickerBroadcast != null) {
            unregisterReceiver(mTimePickerBroadcast);
        }
        if (SharedPrefUtil.getBoolean(Const.CONFIG_NO_KILL, false))
            // 被系统干掉后，服务重启
            WeatherUpdateService_.intent(getApplicationContext()).start();
        super.onDestroy();
    }
}
