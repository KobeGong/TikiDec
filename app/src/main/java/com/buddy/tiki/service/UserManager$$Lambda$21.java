package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$21 implements OnSuccess {
    private final Realm f976a;

    private UserManager$$Lambda$21(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new UserManager$$Lambda$21(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
