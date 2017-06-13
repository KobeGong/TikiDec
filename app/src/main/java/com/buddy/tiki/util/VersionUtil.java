package com.buddy.tiki.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionUtil {
    public static String getAppVersion() {
        return "1.2.11";
    }

    public static int compareVersion(String oldVer, String newVer) {
        int ret = 0;
        if (TextUtils.isEmpty(oldVer) || TextUtils.isEmpty(newVer)) {
            return 0;
        }
        Pattern m = Pattern.compile("(\\d+)\\.(\\d+)(\\.(\\d+))?");
        Matcher oldMatcher = m.matcher(oldVer);
        Matcher newMatcher = m.matcher(newVer);
        if (oldMatcher.find() && newMatcher.find()) {
            int curV1 = m1626a(oldMatcher.group(1));
            int curV2 = m1626a(oldMatcher.group(2));
            curV1 = ((curV1 << 20) | (curV2 << 10)) | m1626a(oldMatcher.group(4));
            int newV1 = ((m1626a(newMatcher.group(1)) << 20) | (m1626a(newMatcher.group(2)) << 10)) | m1626a(newMatcher.group(4));
            ret = curV1 < newV1 ? -1 : curV1 == newV1 ? 0 : 1;
        }
        return ret;
    }

    private static int m1626a(String v) {
        int value = 0;
        if (!TextUtils.isEmpty(v)) {
            try {
                value = Integer.parseInt(v);
            } catch (Exception e) {
            }
        }
        return value;
    }
}
