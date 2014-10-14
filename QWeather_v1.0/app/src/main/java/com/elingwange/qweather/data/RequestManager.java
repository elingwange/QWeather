package com.elingwange.qweather.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.elingwange.qweather.App;

/**
 * Created by elingwange on 2014/10/10.
 */
public class RequestManager {

    public static RequestQueue mRequestQueue = Volley.newRequestQueue(App.getContext());

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
