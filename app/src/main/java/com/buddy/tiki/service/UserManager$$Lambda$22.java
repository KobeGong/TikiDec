package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$22 implements OnError {
    private final Realm f977a;

    private UserManager$$Lambda$22(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new UserManager$$Lambda$22(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
