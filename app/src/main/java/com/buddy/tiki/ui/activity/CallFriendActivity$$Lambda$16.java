package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$16 implements Transaction {
    private final CallFriendActivity f1302a;
    private final String f1303b;
    private final long f1304c;
    private final String f1305d;
    private final long f1306e;
    private final User f1307f;

    private CallFriendActivity$$Lambda$16(CallFriendActivity callFriendActivity, String str, long j, String str2, long j2, User user) {
        this.a = callFriendActivity;
        this.b = str;
        this.c = j;
        this.d = str2;
        this.e = j2;
        this.f = user;
    }

    public static Transaction lambdaFactory$(CallFriendActivity callFriendActivity, String str, long j, String str2, long j2, User user) {
        return new CallFriendActivity$$Lambda$16(callFriendActivity, str, j, str2, j2, user);
    }

    @Hidden
    public void execute(Realm realm) {
        this.a.m698a(this.b, this.c, this.d, this.e, this.f, realm);
    }
}
