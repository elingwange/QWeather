package elingwange.android.quantum.weather.util;

import android.app.Activity;
import android.widget.Toast;

import elingwange.android.quantum.weather.App;
import elingwange.android.quantum.weather.widget.crouton.Crouton;
import elingwange.android.quantum.weather.widget.crouton.Style;

/**
 * Created by elingwange on 2014/10/10.
 */
public class ToastUtil {

    /**
     * 系统Google提示
     */

    public static void showShort(int resid) {
        Toast.makeText(App.getContext(), resid, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resid) {
        Toast.makeText(App.getContext(), resid, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义Crouton提示
     */

    public static void showShort(Activity activity, int resid,int viewGrougResid) {
        showShort(activity, resid, null, viewGrougResid);
    }

    public static void showShort(Activity activity, int resid, Style style, int viewGrougResid) {
        if (style == null) {
            style = Style.ALERT;
        }
        Crouton.makeText(activity, App.getContext().getString(resid), style, viewGrougResid).show();
    }

    public static void showShort(Activity activity, String msg, int viewGrougResid) {
        showShort(activity, msg, null, viewGrougResid);
    }

    public static void showShort(Activity activity, String msg, Style style, int viewGrougResid) {
        if (style == null) {
            style = Style.ALERT;
        }
        Crouton.makeText(activity, msg, style, viewGrougResid).show();
    }
}
