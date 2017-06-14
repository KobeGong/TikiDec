package im.facechat;

import android.content.Context;
import im.facechat.common.p044a.C1636b;

/* compiled from: FacechatRtcConfig */
class C1681i {
    private final Context f11325a;
    private final C1631a f11326b;
    private final FCBaseParams f11327c;

    /* compiled from: FacechatRtcConfig */
    protected static class C1680a {
        private Context f11322a;
        private C1631a f11323b;
        private FCBaseParams f11324c;

        public C1680a(Context context) {
            this.f11322a = context;
        }

        public C1680a m8054a(C1631a c1631a) {
            this.f11323b = c1631a;
            return this;
        }

        public C1680a m8053a(FCBaseParams fCBaseParams) {
            this.f11324c = fCBaseParams;
            return this;
        }

        public C1681i m8055a() {
            return new C1681i();
        }
    }

    private C1681i(C1680a c1680a) {
        this.f11325a = (Context) C1636b.m7896a(c1680a.f11322a);
        this.f11326b = c1680a.f11323b;
        this.f11327c = c1680a.f11324c;
    }

    public C1631a m8056a() {
        return this.f11326b;
    }

    public FCBaseParams m8057b() {
        return this.f11327c;
    }
}
