package com.buddy.tiki.ui.fragment;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$32 implements OnSuccess {
    private final Realm f2074a;

    private ChatMessageFragment$$Lambda$32(Realm realm) {
        this.a = realm;
    }

    public static OnSuccess lambdaFactory$(Realm realm) {
        return new ChatMessageFragment$$Lambda$32(realm);
    }

    @Hidden
    public void onSuccess() {
        this.a.close();
    }
}
