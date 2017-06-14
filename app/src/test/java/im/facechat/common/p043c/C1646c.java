package im.facechat.common.p043c;

import android.content.Context;
import android.support.annotation.NonNull;

/* compiled from: ContextHolder */
public class C1646c {
    private Context f11230a;

    /* compiled from: ContextHolder */
    private static class C1645a {
        private static final C1646c f11229a = new C1646c();
    }

    private C1646c() {
    }

    public static C1646c m7950a() {
        return C1645a.f11229a;
    }

    public void m7951a(@NonNull Context context) {
        this.f11230a = context.getApplicationContext();
    }

    public Context m7952b() {
        return this.f11230a;
    }
}
