package com.elingwange.qweather;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elingwange on 2014/10/01..
 */
@EApplication
public class App extends Application {

    protected final static String TAG = "App";

    private static Context mContext;

    @AfterInject
    void init() {

        this.mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
