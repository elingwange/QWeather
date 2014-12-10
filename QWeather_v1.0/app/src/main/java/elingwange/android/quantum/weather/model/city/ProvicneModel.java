package elingwange.android.quantum.weather.model.city;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elingwange on 2014/11/20.
 */
public class ProvicneModel extends BaseCityModel {

    /**
     * 城市列表
     */
    private List<CityModel> cityModels;

    public ProvicneModel() {
        cityModels = new ArrayList<CityModel>();
    }

    public List<CityModel> getCityModels() {
        return cityModels;
    }

    public void setCityModels(List<CityModel> cityModels) {
        this.cityModels = cityModels;
    }

    @Override
    public String toString() {
        return "ProvicneModel{" +
                "cityModels=" + cityModels +
                '}';
    }
}
