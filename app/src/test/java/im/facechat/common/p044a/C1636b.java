package im.facechat.common.p044a;

import android.support.annotation.Nullable;

/* compiled from: Preconditions */
public final class C1636b {
    public static <T> T m7896a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T m7897a(T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
