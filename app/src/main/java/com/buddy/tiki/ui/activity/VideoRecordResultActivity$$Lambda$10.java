package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$10 implements OnSuccess {
    private final Realm f1557a;

    private VideoRecordResultActivity$$Lambda$10(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new VideoRecordResultActivity$$Lambda$10(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
