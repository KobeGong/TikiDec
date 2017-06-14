package im.facechat;

import android.text.TextUtils;

public class ParamUtil {
    private static native String generateApnsToken(String str, String str2, int i, String str3, String str4, long j, boolean z, boolean z2);

    private static native String generateTopConfigUrl(FCBaseParams fCBaseParams, long j, String str, String str2, String str3, long j2, boolean z, boolean z2);

    private static native String generateWss(FCBaseParams fCBaseParams, String str, String str2, String str3, String str4, String str5, String str6);

    private static native String generateWssEmpty(FCBaseParams fCBaseParams, String str, String str2, String str3);

    static {
        System.loadLibrary("networkhelper");
    }

    static String m7741a(FCBaseParams fCBaseParams, C1729s c1729s, long j, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (c1729s == null || TextUtils.isEmpty(c1729s.m8220a()) || TextUtils.isEmpty(c1729s.m8224c())) {
            return generateTopConfigUrl(fCBaseParams, currentTimeMillis, null, null, null, 0, z, false);
        }
        return generateTopConfigUrl(fCBaseParams, currentTimeMillis, c1729s.m8220a(), c1729s.m8224c(), null, j, z, false);
    }

    static String m7743a(FCBaseParams fCBaseParams, String str, String str2, String str3, String str4, String str5, String str6) {
        return generateWss(fCBaseParams, str, str2, str3, str4, str5, str6);
    }

    static String m7742a(FCBaseParams fCBaseParams, String str, String str2, String str3) {
        return generateWssEmpty(fCBaseParams, str, str2, str3);
    }

    static String m7744a(String str, String str2, int i, String str3, String str4) {
        return generateApnsToken(str, str2, i, str3, str4, System.currentTimeMillis(), true, false);
    }
}
