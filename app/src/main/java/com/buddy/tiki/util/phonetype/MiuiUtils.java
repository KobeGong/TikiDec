package com.buddy.tiki.util.phonetype;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.util.Log;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.postproc;

public class MiuiUtils {
    public static int getMiuiVersion() {
        String version = RomUtils.getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e("MiuiUtils", "get miui version code error, version : " + version);
                Log.e("MiuiUtils", Log.getStackTraceString(e));
            }
        }
        return -1;
    }

    public static boolean checkFloatWindowPermission(Context context) {
        if (VERSION.SDK_INT >= 19) {
            return m1629a(context, 24);
        }
        return true;
    }

    @TargetApi(19)
    private static boolean m1629a(Context context, int op) {
        if (VERSION.SDK_INT >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService("appops");
            try {
                if (((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(manager, new Object[]{Integer.valueOf(op), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.e("MiuiUtils", Log.getStackTraceString(e));
            }
        } else {
            Log.e("MiuiUtils", "Below API 19 cannot invoke!");
            return false;
        }
    }

    public static void applyMiuiPermission(Context context) {
        int versionCode = getMiuiVersion();
        if (versionCode == 5) {
            goToMiuiPermissionActivity_V5(context);
        } else if (versionCode == 6) {
            goToMiuiPermissionActivity_V6(context);
        } else if (versionCode == 7) {
            goToMiuiPermissionActivity_V7(context);
        } else if (versionCode == 8) {
            goToMiuiPermissionActivity_V8(context);
        } else {
            Log.e("MiuiUtils", "this is a special MIUI rom version, its version code " + versionCode);
        }
    }

    private static boolean m1630a(Intent intent, Context context) {
        if (intent != null && context.getPackageManager().queryIntentActivities(intent, avutil.AV_CPU_FLAG_SETEND).size() > 0) {
            return true;
        }
        return false;
    }

    public static void goToMiuiPermissionActivity_V5(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", packageName, null));
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        if (m1630a(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("MiuiUtils", "intent is not available!");
        }
    }

    public static void goToMiuiPermissionActivity_V6(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        if (m1630a(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("MiuiUtils", "Intent is not available!");
        }
    }

    public static void goToMiuiPermissionActivity_V7(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        if (m1630a(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("MiuiUtils", "Intent is not available!");
        }
    }

    public static void goToMiuiPermissionActivity_V8(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        if (m1630a(intent, context)) {
            context.startActivity(intent);
            return;
        }
        intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setPackage("com.miui.securitycenter");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
        if (m1630a(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("MiuiUtils", "Intent is not available!");
        }
    }
}
