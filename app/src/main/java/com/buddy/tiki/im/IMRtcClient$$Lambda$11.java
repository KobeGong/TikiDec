package com.buddy.tiki.im;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$11 implements OnError {
    private final Realm f810a;

    private IMRtcClient$$Lambda$11(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new IMRtcClient$$Lambda$11(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
