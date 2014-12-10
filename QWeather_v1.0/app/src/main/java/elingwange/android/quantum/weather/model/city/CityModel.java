package elingwange.android.quantum.weather.model.city;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elingwange on 2014/11/20.
 */
public class CityModel extends BaseCityModel {

    /**
     * 下级区/县
     */
    private List<AreaModel> areaModels;

    public CityModel() {
        areaModels = new ArrayList<AreaModel>();
    }

    public List<AreaModel> getAreaModels() {
        return areaModels;
    }

    public void setAreaModels(List<AreaModel> areaModels) {
        this.areaModels = areaModels;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "areaModels=" + areaModels +
                '}';
    }
}
