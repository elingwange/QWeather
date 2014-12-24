package elingwange.android.quantum.weather.ui.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import elingwange.android.quantum.weather.R;
import elingwange.android.quantum.weather.model.Weather;
import elingwange.android.quantum.weather.ui.adapter.HourlyAdapter;
import elingwange.android.quantum.weather.widget.jazzylistview.JazzyListView;

/**
 * Created by elingwange on 2014/11/10.
 */
@EFragment(R.layout.fragment_hourly)
public class HourlyFragment extends BaseFragment {

    @ViewById(R.id.lv_hourly)
    JazzyListView mListView;

    @Bean
    HourlyAdapter mAdapter;

    @AfterViews
    void initFragment() {
        initView();
    }

    private void initView() {
        mListView.setAdapter(mAdapter);
    }

    public void updateView(List<Weather> list) {
        mAdapter.appendToList(list);
    }

}
