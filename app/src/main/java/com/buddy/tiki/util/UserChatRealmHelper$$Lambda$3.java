package com.buddy.tiki.util;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserChatRealmHelper$$Lambda$3 implements OnError {
    private final Realm f2479a;

    private UserChatRealmHelper$$Lambda$3(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new UserChatRealmHelper$$Lambda$3(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
