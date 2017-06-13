package com.buddy.tiki.helper;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$11 implements OnSuccess {
    private final Realm f734a;

    private UploadHelper$1$$Lambda$11(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new UploadHelper$1$$Lambda$11(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
