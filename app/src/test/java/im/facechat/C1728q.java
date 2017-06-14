package im.facechat;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import im.facechat.common.p043c.C1646c;
import tv.danmaku.ijk.media.player.BuildConfig;

/* compiled from: PreferenceUtil */
final class C1728q {
    private static SharedPreferences m8218f() {
        return C1646c.m7950a().m7952b().getSharedPreferences("river_xml", 0);
    }

    public static void m8209a(@NonNull String str) {
        C1728q.m8218f().edit().putString("RIVER_ID_KEY", str).apply();
    }

    public static String m8207a() {
        return C1728q.m8218f().getString("RIVER_ID_KEY", BuildConfig.VERSION_NAME);
    }

    public static void m8211b(@NonNull String str) {
        C1728q.m8218f().edit().putString("RIVER_TOKEN_KEY", str).apply();
    }

    public static String m8210b() {
        return C1728q.m8218f().getString("RIVER_TOKEN_KEY", BuildConfig.VERSION_NAME);
    }

    public static void m8213c(@NonNull String str) {
        C1728q.m8218f().edit().putString("RIVER_SIGN_KEY", str).apply();
    }

    public static long m8212c() {
        return C1728q.m8218f().getLong("LTT_KEY", 0);
    }

    public static void m8208a(long j) {
        C1728q.m8218f().edit().putLong("LTT_KEY", j).apply();
    }

    public static void m8215d(String str) {
        C1728q.m8218f().edit().putString("CONFIG_KEY", str).apply();
    }

    public static String m8214d() {
        return C1728q.m8218f().getString("COOKIE_KEY", BuildConfig.VERSION_NAME);
    }

    public static void m8217e(String str) {
        C1728q.m8218f().edit().putString("COOKIE_KEY", str).apply();
    }

    public static void m8219f(String str) {
        C1728q.m8218f().edit().putString("appid_key", str).apply();
    }

    public static String m8216e() {
        return C1728q.m8218f().getString("appid_key", BuildConfig.VERSION_NAME);
    }
}
