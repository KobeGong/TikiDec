package com.buddy.tiki.ui.fragment;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnError;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$33 implements OnError {
    private final Realm f2075a;

    private ChatMessageFragment$$Lambda$33(Realm realm) {
        this.a = realm;
    }

    public static OnError lambdaFactory$(Realm realm) {
        return new ChatMessageFragment$$Lambda$33(realm);
    }

    @Hidden
    public void onError(Throwable th) {
        this.a.close();
    }
}
