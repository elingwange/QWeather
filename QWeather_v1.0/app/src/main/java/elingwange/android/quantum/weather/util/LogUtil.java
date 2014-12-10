package elingwange.android.quantum.weather.util;

import elingwange.android.quantum.weather.App;
import elingwange.android.quantum.weather.Const;

/**
 * Created by elingwange on 2014/11/25.
 */
public class LogUtil {
    public static void i(String tag, int resId) {
        i(tag, App.getContext().getString(resId));
    }

    public static void i(String tag, String msg) {
        if (Const.DEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void d(String tag, int resId) {
        d(tag, App.getContext().getString(resId));
    }

    public static void d(String tag, String msg) {
        if (Const.DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void v(String tag, int resId) {
        v(tag, App.getContext().getString(resId));
    }

    public static void v(String tag, String msg) {
        if (Const.DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void w(String tag, int resId) {
        w(tag, App.getContext().getString(resId));
    }

    public static void w(String tag, String msg) {
        if (Const.DEBUG) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void e(String tag, int resId) {
        e(tag, App.getContext().getString(resId));
    }

    public static void e(String tag, String msg) {
        if (Const.DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }


}
