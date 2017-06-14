package im.facechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import im.facechat.common.p043c.C1646c;
import im.facechat.common.p043c.C1649g;

public class FacechatBroadcastManager {
    @Keep
    public static final String ACTION_FAIL = "im.facechat.ACTION_FAIL";
    @Keep
    public static final String ACTION_TOKEN = "im.facechat.ACTION_TOKEN";

    private FacechatBroadcastManager() {
    }

    static void m7737a(@NonNull Intent intent) {
        LocalBroadcastManager.getInstance(C1646c.m7950a().m7952b()).sendBroadcast(intent);
    }

    static void m7739a(String str, Bundle bundle) {
        Intent intent = new Intent(str);
        intent.putExtra("FACECHAT_VALUE", bundle);
        m7737a(intent);
    }

    static void m7738a(String str, int i, String str2) {
        m7739a(str, C1649g.m7957a(i, str2));
    }

    static void m7740a(String str, String str2) {
        m7739a(str, C1649g.m7958a(str2));
    }
}
