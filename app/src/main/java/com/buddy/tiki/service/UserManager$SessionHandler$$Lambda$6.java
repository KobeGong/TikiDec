package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$SessionHandler$$Lambda$6 implements OnError {
    private final Realm f1007a;

    private UserManager$SessionHandler$$Lambda$6(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new UserManager$SessionHandler$$Lambda$6(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
