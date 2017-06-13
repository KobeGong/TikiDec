package com.buddy.tiki.util.phonetype;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v7.recyclerview.BuildConfig;
import android.util.Log;
import android.widget.Toast;
import org.bytedeco.javacpp.postproc;

public class HuaweiUtils {
    private static final String[] f2485a = new String[]{"GRA-L00", "GRA-L09", BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME};

    public static boolean isHuawei() {
        return Build.BRAND.equalsIgnoreCase("HUAWEI");
    }

    public static boolean checkFloatWindowPermission(Context context) {
        if (VERSION.SDK_INT >= 19) {
            return m1627a(context, 24);
        }
        return true;
    }

    public static void applyPermission(Context context) {
        Intent intent;
        try {
            intent = new Intent();
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"));
            if (RomUtils.getEmuiVersion() == 3.1d) {
                context.startActivity(intent);
                return;
            }
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity"));
            context.startActivity(intent);
        } catch (SecurityException e) {
            intent = new Intent();
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
            context.startActivity(intent);
            Log.e("HuaweiUtils", Log.getStackTraceString(e));
        } catch (ActivityNotFoundException e2) {
            intent = new Intent();
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            intent.setComponent(new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem"));
            context.startActivity(intent);
            e2.printStackTrace();
            Log.e("HuaweiUtils", Log.getStackTraceString(e2));
        } catch (Exception e3) {
            Toast.makeText(context, "\u8fdb\u5165\u8bbe\u7f6e\u9875\u9762\u5931\u8d25\uff0c\u8bf7\u624b\u52a8\u8bbe\u7f6e", 1).show();
            Log.e("HuaweiUtils", Log.getStackTraceString(e3));
        }
    }

    @TargetApi(19)
    private static boolean m1627a(Context context, int op) {
        if (VERSION.SDK_INT >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService("appops");
            try {
                if (((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(manager, new Object[]{Integer.valueOf(op), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.e("HuaweiUtils", Log.getStackTraceString(e));
            }
        } else {
            Log.e("HuaweiUtils", "Below API 19 cannot invoke!");
            return false;
        }
    }
}
