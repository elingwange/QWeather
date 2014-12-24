package elingwange.android.quantum.weather.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import elingwange.android.quantum.weather.widget.RealtimeWeatherItem;
import elingwange.android.quantum.weather.widget.RealtimeWeatherItem;
import elingwange.android.quantum.weather.widget.jazzyviewpager.JazzyViewPager;
import elingwange.android.quantum.weather.widget.jazzyviewpager.OutlineContainer;

/**
 * Created by elingwange on 2014/12/10.
 */
public class RealtimeAdapter extends PagerAdapter {

    private final List<RealtimeWeatherItem> mList;
    private JazzyViewPager mViewPager;

    public RealtimeAdapter(List<RealtimeWeatherItem> list, JazzyViewPager viewPager) {
        mList = list;
        mViewPager = viewPager;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mViewPager.setObjectForPosition(mList.get(position), position);
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewPager.findViewFromObject(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (view instanceof OutlineContainer) {
            return ((OutlineContainer) view).getChildAt(0) == object;
        } else {
            return view == object;
        }
    }

}
