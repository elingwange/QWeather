package elingwange.android.quantum.weather.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

import elingwange.android.quantum.weather.App;
import elingwange.android.quantum.weather.Const;
import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.model.city.AreaModel;
import elingwange.android.quantum.weather.util.LogUtil;
import elingwange.android.quantum.weather.util.NetUtil;
import elingwange.android.quantum.weather.util.SharedPrefUtil;

/**
 * Created by elingwange on 2014/12/15.
 */
@Fullscreen
@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    protected  final static String TAG = "SplashActivity";

    protected final static int START_TIME = 2 * 1000;

    @AfterViews
    void initActivity() {

        isFirst();

        // 检测是否开启自动检测更新
//        if (SharedPrefUtil.getBoolean(Const.CONFIG_AUTO_UPDATE, true) && NetUtil.isNetworkConnected()) {
//            updateApk();
//        } else {
//            isFirst();
//        }
    }

    private void isFirst() {

        if (SharedPrefUtil.isFrast()) {
            SharedPrefUtil.putBoolean(Const.CONFIG_AUTO_UPDATE, true);
            //添加一个默认城市-北京
            App.addMyArea(new AreaModel(Const.DEF_CITY_ID, Const.DEF_CITY_NAME, Const.DEF_WEATHER_CODE));
            //进入引导界面
            start(GuideActivity_.class);
        } else {
            //进入应用主界面
            start(MainActivity_.class);
        }
    }

    /**
     * 启动应用
     */
    private void start(final Class clazz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, clazz);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, START_TIME);
    }

    /**
     * 友盟更新
     */
    private void updateApk() {
        UmengUpdateAgent.setDefault();
        UmengUpdateAgent.update(SplashActivity.this);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.No: // has no update
                        isFirst();
                        break;
                }
            }
        });
        UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {
            @Override
            public void onClick(int i) {

                // 进入下界面
                isFirst();

                // 打印日志
                switch (i) {
                    case UpdateStatus.Update:
                        // 用户选择现在更新;
                        LogUtil.i(TAG, "Update");
                        break;
                    case UpdateStatus.Ignore:
                        // 用户选择忽略该版;
                        LogUtil.i(TAG, "Ignore");
                        break;
                    case UpdateStatus.NotNow:
                        // 用户选择以后再说，点击回退键，关闭对话框。
                        LogUtil.i(TAG, "NotNow");
                        break;
                }
            }
        });
    }
}
