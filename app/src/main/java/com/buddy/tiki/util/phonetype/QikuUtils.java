package com.buddy.tiki.util.phonetype;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.support.v7.recyclerview.BuildConfig;
import android.util.Log;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.postproc;

public class QikuUtils {
    public static boolean checkFloatWindowPermission(Context context) {
        if (VERSION.SDK_INT >= 19) {
            return m1631a(context, 24);
        }
        return true;
    }

    @TargetApi(19)
    private static boolean m1631a(Context context, int op) {
        if (VERSION.SDK_INT >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService("appops");
            try {
                if (((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(manager, new Object[]{Integer.valueOf(op), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.e("QikuUtils", Log.getStackTraceString(e));
            }
        } else {
            Log.e(BuildConfig.VERSION_NAME, "Below API 19 cannot invoke!");
            return false;
        }
    }

    public static void applyPermission(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        if (m1632a(intent, context)) {
            context.startActivity(intent);
            return;
        }
        intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
        if (m1632a(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("QikuUtils", "can't open permission page with particular name, please use \"adb shell dumpsys activity\" command and tell me the name of the float window permission page");
        }
    }

    private static boolean m1632a(Intent intent, Context context) {
        if (intent != null && context.getPackageManager().queryIntentActivities(intent, avutil.AV_CPU_FLAG_SETEND).size() > 0) {
            return true;
        }
        return false;
    }
}
