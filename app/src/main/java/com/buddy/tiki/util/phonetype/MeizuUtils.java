package com.buddy.tiki.util.phonetype;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import org.bytedeco.javacpp.postproc;

public class MeizuUtils {
    public static boolean checkFloatWindowPermission(Context context) {
        if (VERSION.SDK_INT >= 19) {
            return m1628a(context, 24);
        }
        return true;
    }

    public static void applyPermission(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        context.startActivity(intent);
    }

    @TargetApi(19)
    private static boolean m1628a(Context context, int op) {
        if (VERSION.SDK_INT >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService("appops");
            try {
                if (((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(manager, new Object[]{Integer.valueOf(op), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.e("MeizuUtils", Log.getStackTraceString(e));
            }
        } else {
            Log.e("MeizuUtils", "Below API 19 cannot invoke!");
            return false;
        }
    }

    public static boolean isMeizu() {
        return Build.BRAND.equalsIgnoreCase("Meizu");
    }

    public static boolean isMX1() {
        String model = Build.MODEL;
        return !TextUtils.isEmpty(model) && isMeizu() && model.equalsIgnoreCase("m030");
    }

    public static boolean isMX4() {
        String model = Build.MODEL;
        return !TextUtils.isEmpty(model) && isMeizu() && model.equalsIgnoreCase("mx4");
    }
}
