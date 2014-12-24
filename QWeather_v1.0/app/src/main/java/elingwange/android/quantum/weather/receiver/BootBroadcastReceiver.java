package elingwange.android.quantum.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import elingwange.android.quantum.weather.service.WeatherUpdateService_;
import elingwange.android.quantum.weather.util.LogUtil;

/**
 * Created by elingwange on 2014/12/10.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

    protected final static String TAG = "BootBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtil.i(TAG, intent.getAction());

        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            // 用户唤醒设备时启动
            WeatherUpdateService_.intent(context).start();
        } else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            // 开机时
            WeatherUpdateService_.intent(context).start();
        }
    }

}
