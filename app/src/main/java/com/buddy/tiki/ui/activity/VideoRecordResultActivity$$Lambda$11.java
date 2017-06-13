package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$11 implements OnError {
    private final Realm f1558a;

    private VideoRecordResultActivity$$Lambda$11(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new VideoRecordResultActivity$$Lambda$11(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
