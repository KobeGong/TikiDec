package com.buddy.tiki.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import com.buddy.tiki.ChatApp;
import com.buddy.tiki.model.constant.ChannelKeys;

public class DisplayUtil {
    private static DisplayMetrics f2360a;
    private static int f2361b;

    public static int getDisplayWidth() {
        return m1532a().widthPixels;
    }

    public static int getDisplayHeight() {
        return m1532a().heightPixels;
    }

    private static DisplayMetrics m1532a() {
        if (f2360a == null) {
            f2360a = ChatApp.getInstance().getResources().getDisplayMetrics();
        }
        return f2360a;
    }

    public static float getDensity() {
        return m1532a().density;
    }

    public static int[] getScreenWidthHeight() {
        return new int[]{m1532a().widthPixels, m1532a().heightPixels};
    }

    public static int dip2px(float dpValue) {
        return (int) (getDensity() * dpValue);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity());
    }

    public static int sp2px(float dpValue) {
        return (int) (getDensity() * dpValue);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / getDensity());
    }

    public static int getStatusBarHeight(Activity activity) {
        try {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            f2361b = rect.top;
        } catch (Exception e) {
        }
        return f2361b;
    }

    public static int getStatusBarHeight() {
        try {
            Class<?> aClass = Class.forName("com.android.internal.R$dimen");
            Object obj = aClass.newInstance();
            f2361b = ChatApp.getInstance().getResources().getDimensionPixelSize(Integer.parseInt(aClass.getField("status_bar_height").get(obj).toString()));
        } catch (Exception e) {
        }
        return f2361b;
    }

    public static int[] getLocationOnScreen(View view) {
        if (view == null) {
            return new int[2];
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    public static boolean hasNaviBar(Activity activity) {
        if (activity == null) {
            return false;
        }
        Resources resources = activity.getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", ChannelKeys.ANDROID);
        if (id > 0) {
            return resources.getBoolean(id);
        }
        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(4);
        if (hasMenuKey || hasBackKey) {
            return false;
        }
        return true;
    }

    public static int getRawWidth(Activity activity) {
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        try {
            if (VERSION.SDK_INT >= 17) {
                Point point = new Point();
                display.getRealSize(point);
                return point.x;
            } else if (VERSION.SDK_INT >= 14) {
                return ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return display.getWidth();
        }
    }

    public static int getRawHeight(Activity activity) {
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        try {
            if (VERSION.SDK_INT >= 17) {
                Point point = new Point();
                display.getRealSize(point);
                return point.y;
            } else if (VERSION.SDK_INT >= 14) {
                return ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(display, new Object[0])).intValue();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return display.getWidth();
        }
    }

    public static int getNavigationBarHeight(AppCompatActivity activity) {
        if (!hasNaviBar(activity)) {
            return 0;
        }
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", ChannelKeys.ANDROID);
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
