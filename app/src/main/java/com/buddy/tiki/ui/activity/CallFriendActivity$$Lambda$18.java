package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$18 implements OnError {
    private final Realm f1309a;

    private CallFriendActivity$$Lambda$18(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new CallFriendActivity$$Lambda$18(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
