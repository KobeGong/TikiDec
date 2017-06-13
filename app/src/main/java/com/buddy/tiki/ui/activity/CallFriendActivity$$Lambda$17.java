package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$17 implements OnSuccess {
    private final Realm f1308a;

    private CallFriendActivity$$Lambda$17(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new CallFriendActivity$$Lambda$17(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
