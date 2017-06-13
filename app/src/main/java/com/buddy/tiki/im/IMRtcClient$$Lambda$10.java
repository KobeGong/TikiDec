package com.buddy.tiki.im;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$10 implements OnSuccess {
    private final Realm f809a;

    private IMRtcClient$$Lambda$10(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new IMRtcClient$$Lambda$10(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
