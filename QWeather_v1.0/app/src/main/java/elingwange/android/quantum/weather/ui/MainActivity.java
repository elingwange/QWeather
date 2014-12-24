package elingwange.android.quantum.weather.ui;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import elingwange.android.quantum.weather.Const;
import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.model.Weather;
import elingwange.android.quantum.weather.ui.fragment.BaseFragment;
import elingwange.android.quantum.weather.ui.fragment.DrawerFragment;
import elingwange.android.quantum.weather.ui.fragment.DrawerFragment_;
import elingwange.android.quantum.weather.ui.fragment.HomeFragment;
import elingwange.android.quantum.weather.ui.fragment.HomeFragment_;
import elingwange.android.quantum.weather.ui.fragment.HourlyFragment;
import elingwange.android.quantum.weather.ui.fragment.HourlyFragment_;
import elingwange.android.quantum.weather.util.ActivityUtil;
import elingwange.android.quantum.weather.util.SharedPrefUtil;
import elingwange.android.quantum.weather.widget.FoldingDrawerLayout;

/**
 * Created by elingwange on 2014/12/15.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    protected final static String TAG = "MainActivity";

    private Menu mMenu;

    public HomeFragment homeFragment;

    private DrawerFragment leftDrawerFragment;
    private HourlyFragment rightDrawerFragment;

    /**
     * 侧拉菜单
     */
    @ViewById(R.id.drawer_layout)
    FoldingDrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    /**
     * 天气列表
     */
    @ViewById(R.id.lv_weather)
    ListView mWeatherListView;

    @AfterViews
    void initActivity() {

        initData();
        initView();
    }

    private void initData() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mMenu.findItem(R.id.action_refresh).setVisible(false);
                if (homeFragment.getCurSunModel() != null);
                    leftDrawerFragment.updateReminder(homeFragment.getCurSunModel());
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mMenu.findItem(R.id.action_refresh).setVisible(true);
            }
        };
    }

    private void initView() {

        actionBar.setIcon(R.drawable.ic_actionbar);

        mDrawerLayout.setScrimColor(Color.argb(100, 0, 0, 0));
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        replaceFragment(R.id.fl_content, homeFragment = new HomeFragment_());

        replaceFragment(R.id.fl_left_drawer, leftDrawerFragment = new DrawerFragment_());
        replaceFragment(R.id.fl_right_drawer, rightDrawerFragment = new HourlyFragment_());

        homeFragment.setActivity(this);
    }

    protected void replaceFragment(int viewId, BaseFragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    public void updateHourly(List<Weather> list) {
        rightDrawerFragment.updateView(list);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_refresh:
                homeFragment.setOnRefresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 杀死进程
        if (SharedPrefUtil.getBoolean(Const.CONFIG_EXIT_KILL, false))
            ActivityUtil.finishKill();
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}