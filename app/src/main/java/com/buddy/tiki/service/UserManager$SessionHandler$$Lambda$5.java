package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$SessionHandler$$Lambda$5 implements OnSuccess {
    private final Realm f1006a;

    private UserManager$SessionHandler$$Lambda$5(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new UserManager$SessionHandler$$Lambda$5(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
