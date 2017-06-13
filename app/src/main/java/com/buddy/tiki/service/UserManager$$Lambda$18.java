package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$18 implements OnSuccess {
    private final Realm f970a;

    private UserManager$$Lambda$18(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new UserManager$$Lambda$18(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
