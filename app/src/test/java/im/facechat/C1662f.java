package im.facechat;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import im.facechat.C1725o.C1721c;

/* compiled from: CameraHelper */
class C1662f {
    static C1721c m8004a(@NonNull Context context) {
        PreferenceManager.getDefaultSharedPreferences(context);
        String str = "VP8";
        String str2 = "OPUS";
        int i = 0;
        int i2 = 0;
        String[] split = "1280 x 720".split("[ x]+");
        if (split.length == 2) {
            try {
                i = Integer.parseInt(split[0]);
                i2 = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                i = 0;
                i2 = 0;
            }
        }
        int i3 = 0;
        split = "Default".split("[ x]+");
        if (split.length == 2) {
            try {
                i3 = Integer.parseInt(split[0]);
            } catch (NumberFormatException e2) {
            }
        }
        String str3 = "Default";
        str3 = "Default";
        return new C1721c(true, false, false, false, i, i2, i3, 0, str, true, false, 0, str2, false, false, false, false, false, false, true);
    }
}
