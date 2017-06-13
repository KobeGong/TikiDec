package com.buddy.tiki.helper;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$10 implements OnError {
    private final Realm f706a;

    private UploadHelper$$Lambda$10(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new UploadHelper$$Lambda$10(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
