package elingwange.android.quantum.weather.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by elingwange on 2014/11/25.
 */
public abstract class BaseModel implements Serializable {

    public String toJson() {
        return new Gson().toJson(this);
    }
}
