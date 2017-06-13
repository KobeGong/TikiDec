package com.buddy.tiki.util;

import android.content.Context;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class PhoneStateUtil {
    private static String f2444a;

    public static String getDevieId(Context context) {
        if (f2444a == null) {
            f2444a = DeviceUuidFactory.getDeviceUuid(context).toString();
        }
        return f2444a;
    }

    public static long totalSize(String path) {
        try {
            StatFs statfs = new StatFs(path);
            return (long) (statfs.getBlockCount() * statfs.getBlockSize());
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getDeviceId(Context context) {
        if (context != null) {
            try {
                TelephonyManager manager = (TelephonyManager) context.getSystemService("phone");
                if (manager != null) {
                    String str = (String) manager.getClass().getMethod("getDeviceId", new Class[0]).invoke(manager, new Object[0]);
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static String getAndroidId(Context context) {
        String str = null;
        if (context != null) {
            try {
                str = Secure.getString(context.getContentResolver(), "android_id");
            } catch (Exception e) {
            }
        }
        return str;
    }
}
