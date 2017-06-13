package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$17 implements Transaction {
    private final String f967a;
    private final String f968b;
    private final int f969c;

    private UserManager$$Lambda$17(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public static Transaction lambdaFactory$(String str, String str2, int i) {
        return new UserManager$$Lambda$17(str, str2, i);
    }

    @Hidden
    public void execute(Realm realm) {
        UserManager.m328a(this.a, this.b, this.c, realm);
    }
}
