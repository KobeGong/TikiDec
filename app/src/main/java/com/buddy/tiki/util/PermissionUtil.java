package com.buddy.tiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import com.buddy.tiki.helper.DialogHelper;
import com.buddy.tiki.util.phonetype.HuaweiUtils;
import com.buddy.tiki.util.phonetype.MeizuUtils;
import com.buddy.tiki.util.phonetype.MiuiUtils;
import com.buddy.tiki.util.phonetype.QikuUtils;
import com.buddy.tiki.util.phonetype.RomUtils;
import org.bytedeco.javacpp.postproc;

public class PermissionUtil {
    private static final String[] f2443a = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.INTERNET"};

    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults == null) {
            return true;
        }
        for (int result : grantResults) {
            if (result != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasSelfPermissions(Context context, String... permissions) {
        if (permissions == null) {
            return true;
        }
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(context, permission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasRequiredSelfPermissions(Context context) {
        return hasSelfPermissions(context, f2443a);
    }

    public static boolean checkAlertWindowPermission(@NonNull Context context) {
        if (VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return m1584b(context);
            }
            if (RomUtils.checkIsMeizuRom()) {
                return m1588c(context);
            }
            if (RomUtils.checkIsHuaweiRom()) {
                return m1580a(context);
            }
            if (RomUtils.checkIs360Rom()) {
                return m1592d(context);
            }
        }
        return m1594e(context);
    }

    private static boolean m1580a(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private static boolean m1584b(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private static boolean m1588c(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private static boolean m1592d(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private static boolean m1594e(Context context) {
        if (RomUtils.checkIsMeizuRom()) {
            return m1588c(context);
        }
        Boolean result = Boolean.valueOf(true);
        if (VERSION.SDK_INT >= 23) {
            try {
                result = (Boolean) Settings.class.getDeclaredMethod("canDrawOverlays", new Class[]{Context.class}).invoke(null, new Object[]{context});
            } catch (Exception e) {
            }
        }
        return result.booleanValue();
    }

    public static void applyAlertWindowPermission(Context context, Runnable onCancel) {
        if (VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                m1590d(context, onCancel);
            } else if (RomUtils.checkIsMeizuRom()) {
                m1586c(context, onCancel);
            } else if (RomUtils.checkIsHuaweiRom()) {
                m1582b(context, onCancel);
            } else if (RomUtils.checkIs360Rom()) {
                m1578a(context, onCancel);
            }
        }
        m1595f(context);
    }

    private static void m1578a(Context context, Runnable onCancel) {
        DialogHelper.INSTANCE.showAlertWindowPermission(context, PermissionUtil$$Lambda$1.lambdaFactory$(context), PermissionUtil$$Lambda$2.lambdaFactory$(onCancel));
    }

    static /* synthetic */ void m1591d(Runnable onCancel, DialogInterface dialog, int which) {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private static void m1582b(Context context, Runnable onCancel) {
        DialogHelper.INSTANCE.showAlertWindowPermission(context, PermissionUtil$$Lambda$3.lambdaFactory$(context), PermissionUtil$$Lambda$4.lambdaFactory$(onCancel));
    }

    static /* synthetic */ void m1587c(Runnable onCancel, DialogInterface dialog, int which) {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private static void m1586c(Context context, Runnable onCancel) {
        DialogHelper.INSTANCE.showAlertWindowPermission(context, PermissionUtil$$Lambda$5.lambdaFactory$(context), PermissionUtil$$Lambda$6.lambdaFactory$(onCancel));
    }

    static /* synthetic */ void m1583b(Runnable onCancel, DialogInterface dialog, int which) {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private static void m1590d(Context context, Runnable onCancel) {
        DialogHelper.INSTANCE.showAlertWindowPermission(context, PermissionUtil$$Lambda$7.lambdaFactory$(context), PermissionUtil$$Lambda$8.lambdaFactory$(onCancel));
    }

    static /* synthetic */ void m1579a(Runnable onCancel, DialogInterface dialog, int which) {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private static void m1595f(Context context) {
        if (RomUtils.checkIsMeizuRom()) {
            m1586c(context, null);
        } else if (VERSION.SDK_INT >= 23) {
            DialogHelper.INSTANCE.showAlertWindowPermission(context, PermissionUtil$$Lambda$9.lambdaFactory$(context), null);
        }
    }

    static /* synthetic */ void m1577a(Context context, DialogInterface dialog, int which) {
        try {
            Intent intent = new Intent(Settings.class.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION").get(null).toString());
            intent.setFlags(postproc.PP_CPU_CAPS_ALTIVEC);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
