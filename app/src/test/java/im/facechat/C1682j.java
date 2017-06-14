package im.facechat;

import android.content.Context;
import im.facechat.C1681i.C1680a;
import im.facechat.common.p044a.C1636b;

/* compiled from: FacechatRtcFactory */
class C1682j {
    private static C1682j f11328a;
    private final C1681i f11329b;

    private C1682j(C1681i c1681i) {
        this.f11329b = (C1681i) C1636b.m7896a(c1681i);
    }

    static C1682j m8058a() {
        return (C1682j) C1636b.m7897a(f11328a, "FacechatRtcFactory is not initialize!");
    }

    static void m8059a(Context context, C1631a c1631a, FCBaseParams fCBaseParams) {
        f11328a = new C1682j(new C1680a(context).m8054a(c1631a).m8053a(fCBaseParams).m8055a());
    }

    C1681i m8060b() {
        return this.f11329b;
    }
}
