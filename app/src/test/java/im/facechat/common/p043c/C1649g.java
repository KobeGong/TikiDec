package im.facechat.common.p043c;

import android.os.Bundle;
import android.support.annotation.NonNull;

/* compiled from: ParamWrapper */
public final class C1649g {
    @NonNull
    public static Bundle m7957a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("FACECHAT_ERROR_CODE", i);
        bundle.putString("FACECHAT_ERROR_MESSAGE", str);
        return bundle;
    }

    public static Bundle m7958a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("FACECHAT_TOKEN", str);
        return bundle;
    }
}
