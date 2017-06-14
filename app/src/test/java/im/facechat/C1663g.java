package im.facechat;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings.Secure;
import android.text.TextUtils;

/* compiled from: DeviceUuidFactory */
class C1663g {
    private static String f11273a;

    private C1663g() {
    }

    public static String m8005a(Context context) {
        if (TextUtils.isEmpty(f11273a)) {
            synchronized (C1663g.class) {
                if (TextUtils.isEmpty(f11273a)) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("device_id.xml", 0);
                    f11273a = sharedPreferences.getString("android_id", null);
                    if (TextUtils.isEmpty(f11273a)) {
                        f11273a = Secure.getString(context.getContentResolver(), "android_id");
                        sharedPreferences.edit().putString("android_id", f11273a).apply();
                    } else {
                        String str = f11273a;
                        return str;
                    }
                }
            }
        }
        return f11273a;
    }
}
