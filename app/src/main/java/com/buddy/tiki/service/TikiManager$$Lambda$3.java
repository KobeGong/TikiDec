package com.buddy.tiki.service;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TikiManager$$Lambda$3 implements Transaction {
    private static final TikiManager$$Lambda$3 f942a = new TikiManager$$Lambda$3();

    private TikiManager$$Lambda$3() {
    }

    public static Transaction lambdaFactory$() {
        return a;
    }

    @Hidden
    public void execute(Realm realm) {
        TikiManager.m307a(realm);
    }
}
