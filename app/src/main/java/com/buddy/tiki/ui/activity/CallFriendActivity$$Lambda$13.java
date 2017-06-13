package com.buddy.tiki.ui.activity;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$13 implements Transaction {
    private final CallFriendActivity f1296a;
    private final long f1297b;
    private final String f1298c;
    private final long f1299d;

    private CallFriendActivity$$Lambda$13(CallFriendActivity callFriendActivity, long j, String str, long j2) {
        this.a = callFriendActivity;
        this.b = j;
        this.c = str;
        this.d = j2;
    }

    public static Transaction lambdaFactory$(CallFriendActivity callFriendActivity, long j, String str, long j2) {
        return new CallFriendActivity$$Lambda$13(callFriendActivity, j, str, j2);
    }

    @Hidden
    public void execute(Realm realm) {
        this.a.m692a(this.b, this.c, this.d, realm);
    }
}
