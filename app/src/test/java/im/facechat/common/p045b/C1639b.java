package im.facechat.common.p045b;

/* compiled from: FacechatLog */
public class C1639b {
    private static C1637c f11217a = C1638a.m7903a();

    public static void m7918a(String str, String str2) {
        if (f11217a.mo4056b(3)) {
            f11217a.mo4053a(str, str2);
        }
    }

    public static void m7916a(Class<?> cls, String str) {
        if (f11217a.mo4056b(3)) {
            f11217a.mo4053a(C1639b.m7915a(cls), str);
        }
    }

    public static void m7919b(Class<?> cls, String str) {
        if (f11217a.mo4056b(5)) {
            f11217a.mo4055b(C1639b.m7915a(cls), str);
        }
    }

    public static void m7920b(String str, String str2) {
        if (f11217a.mo4056b(6)) {
            f11217a.mo4057c(str, str2);
        }
    }

    public static void m7921c(Class<?> cls, String str) {
        if (f11217a.mo4056b(6)) {
            f11217a.mo4057c(C1639b.m7915a(cls), str);
        }
    }

    public static void m7917a(Class<?> cls, String str, Throwable th) {
        if (f11217a.mo4056b(6)) {
            f11217a.mo4054a(C1639b.m7915a(cls), str, th);
        }
    }

    private static String m7915a(Class<?> cls) {
        return cls.getSimpleName();
    }
}
