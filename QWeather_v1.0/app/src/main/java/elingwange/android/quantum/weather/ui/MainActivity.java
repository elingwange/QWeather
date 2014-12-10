package elingwange.android.quantum.weather.ui;

import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.ui.fragment.HomeFragment;


/**
 * Created by elingwange on 2014/10/01.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity
{

    protected final static String TAG = "MainActivity";

    public HomeFragment homeFragment;


    @AfterViews
    void initActivity() {

        initData();

        initView();
    }

    private void initData() {

    }

    private void initView() {

    }


}
