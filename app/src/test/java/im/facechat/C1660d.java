package im.facechat;

import android.os.Build;
import android.os.Build.VERSION;
import im.facechat.common.p045b.C1639b;

/* compiled from: AppRTCUtils */
final class C1660d {
    public static void m7998a(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public static String m7996a() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    public static void m7997a(Class<?> cls) {
        C1639b.m7916a((Class) cls, "Android SDK: " + VERSION.SDK_INT + ", Release: " + VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }
}
