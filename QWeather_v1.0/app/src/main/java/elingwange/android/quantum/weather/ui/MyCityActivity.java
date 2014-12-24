package elingwange.android.quantum.weather.ui;

import android.graphics.Point;
import android.view.Display;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import elingwange.android.quantum.weather.App;
import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.ui.adapter.MyCityAdapter;
import elingwange.android.quantum.weather.util.ToastUtil;
import elingwange.android.quantum.weather.widget.swipeback.SwipeBackActivity;
import elingwange.android.quantum.weather.widget.swipelistview.SwipeListView;

/**
 * Created by Ho on 2014/7/3.
 */
@EActivity(R.layout.activiy_mycity)
public class MyCityActivity extends SwipeBackActivity {

    @ViewById(R.id.lv_my_city)
    SwipeListView mListView;

    @Bean
    MyCityAdapter mCityAdapter;

    @AfterViews
    void initActivity() {

        initView();
    }

    private void initView() {

        // 获取屏幕宽度
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;

        // 设置模式支持滑动
        mListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
        mListView.setSwipeActionLeft(0);
        mListView.setSwipeActionRight(0);
        mListView.setOffsetLeft(screenWidth / 4 * 3);
        mListView.setOffsetRight(0);
        mListView.setAnimationTime(0);
        // 支持长按自动呼出
        mListView.setSwipeOpenOnLongPress(true);

        mListView.setAdapter(mCityAdapter);
        mCityAdapter.appendToList(App.getMyArea());
    }

    public void delectCity(int position) {
        if (App.getMyArea().size() <= 1) {
            ToastUtil.showShort(R.string.no_delect);
        } else {
            setResult(1);
            App.removeMyArea(position);
            mCityAdapter.appendToList(App.getMyArea());
            mListView.closeOpenedItems();
        }
    }
}
