package com.buddy.tiki.helper;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$12 implements OnError {
    private final Realm f735a;

    private UploadHelper$1$$Lambda$12(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new UploadHelper$1$$Lambda$12(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
