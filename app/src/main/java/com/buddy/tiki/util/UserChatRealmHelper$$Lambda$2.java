package com.buddy.tiki.util;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserChatRealmHelper$$Lambda$2 implements OnSuccess {
    private final Realm f2478a;

    private UserChatRealmHelper$$Lambda$2(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new UserChatRealmHelper$$Lambda$2(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
