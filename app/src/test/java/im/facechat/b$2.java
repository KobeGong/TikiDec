package im.facechat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import im.facechat.common.p045b.C1639b;
import p042u.aly.dd;

/* compiled from: AppRTCAudioManager */
class b$2 extends BroadcastReceiver {
    final /* synthetic */ b f11194a;

    b$2(b bVar) {
        this.a = bVar;
    }

    public void onReceive(Context context, Intent intent) {
        boolean z;
        int intExtra = intent.getIntExtra("state", 0);
        int intExtra2 = intent.getIntExtra("microphone", 0);
        C1639b.m7916a(b.c(), "BroadcastReceiver.onReceive" + C1660d.m7996a() + ": a=" + intent.getAction() + ", s=" + (intExtra == 0 ? "unplugged" : "plugged") + ", m=" + (intExtra2 == 1 ? "mic" : "no mic") + ", n=" + intent.getStringExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME) + ", sb=" + isInitialStickyBroadcast());
        if (intExtra == 1) {
            z = true;
        } else {
            z = false;
        }
        switch (intExtra) {
            case dd.f15932a /*0*/:
                b.a(this.a, z);
                return;
            case dd.f15933b /*1*/:
                if (b.b(this.a) != b$a.WIRED_HEADSET) {
                    b.a(this.a, z);
                    return;
                }
                return;
            default:
                C1639b.m7921c(b.c(), "Invalid state");
                return;
        }
    }
}
