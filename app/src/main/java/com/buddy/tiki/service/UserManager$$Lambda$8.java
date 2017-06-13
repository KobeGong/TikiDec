package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$8 implements Transaction {
    private final String f1000a;

    private UserManager$$Lambda$8(String str) {
        this.a = str;
    }

    public static Transaction lambdaFactory$(String str) {
        return new UserManager$$Lambda$8(str);
    }

    @Hidden
    public void execute(Realm realm) {
        UserManager.m326a(this.a, realm);
    }
}
