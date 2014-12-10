package elingwange.android.quantum.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by elingwange on 2014/11/25.
 */
public class Weather extends BaseModel {

    /** 图片ID */
    public int img;

    /** 时间 */
    public int hour;

    /** 温度 */
    @SerializedName("temperature")
    public int temp;

    /** 华氏度 */
    public int humidity;

    /** 描述信息 */
    public String info;
}
