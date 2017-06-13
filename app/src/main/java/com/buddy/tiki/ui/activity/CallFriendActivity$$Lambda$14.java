package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$14 implements OnSuccess {
    private final Realm f1300a;

    private CallFriendActivity$$Lambda$14(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new CallFriendActivity$$Lambda$14(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
