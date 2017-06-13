package com.buddy.tiki.util;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserChatRealmHelper$$Lambda$1 implements Transaction {
    private final String f2477a;

    private UserChatRealmHelper$$Lambda$1(String str) {
        this.a = str;
    }

    public static Transaction lambdaFactory$(String str) {
        return new UserChatRealmHelper$$Lambda$1(str);
    }

    @Hidden
    public void execute(Realm realm) {
        UserChatRealmHelper.m1625a(this.a, realm);
    }
}
