package elingwange.android.quantum.weather.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.ui.MainActivity_;


/**
 * Created by elingwange on 2014/10/01.
 */
@Fullscreen
@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    protected  final static String TAG = "SplashActivity";

    protected final static int START_TIME = 2 * 1000;

    @AfterViews
    void initActivity() {

        //进入应用主界面
        start(MainActivity_.class);
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

}
