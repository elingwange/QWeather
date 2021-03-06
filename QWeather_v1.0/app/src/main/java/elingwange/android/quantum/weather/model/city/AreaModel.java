package elingwange.android.quantum.weather.model.city;

import java.io.Serializable;

/**
 * Created by elingwange on 2014/11/20.
 */
public class AreaModel extends BaseCityModel {

    /**
     * 天气ID
     */
    private String weatherCode;

    /**
     * AreaModel构造方法
     */
    public AreaModel() {
        super();
        this.weatherCode = "";
    }

    /**
     * AreaModel构造方法
     * @param cityId
     * @param cityName
     * @param weatherCode
     */
    public AreaModel(String cityId, String cityName, String weatherCode) {
        super(cityId, cityName);
        this.weatherCode = weatherCode;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    @Override
    public String toString() {
        return "AreaModel{" +
                "weatherCode='" + weatherCode + '\'' +
                '}';
    }
}
