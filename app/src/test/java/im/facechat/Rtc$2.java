package im.facechat;

import android.content.Context;
import android.support.annotation.Nullable;
import im.facechat.C1697l.C1616a;
import okhttp3.Callback;

class Rtc$2 implements C1616a {
    final /* synthetic */ Context f11155a;
    final /* synthetic */ FCBaseParams f11156b;
    final /* synthetic */ Callback f11157c;

    Rtc$2(Context context, FCBaseParams fCBaseParams, Callback callback) {
        this.a = context;
        this.b = fCBaseParams;
        this.c = callback;
    }

    public void mo4027a(@Nullable C1729s c1729s) {
        C1703n.m8109a().m8116a(this.a, this.b, c1729s, this.c);
    }
}
