package elingwange.android.quantum.weather.model;

import java.util.List;

/**
 * Created by elingwange on 2014/11/25.
 */
public class WeatherModel extends BaseModel {

    /** 时间 */
    public String date;

    /** 天气信息 */
    public WeatherInfo info;

    public static class WeatherInfo {

        /** 晚上 */
        public List<String> night;

        /** 白天 */
        public List<String> day;
    }
}
