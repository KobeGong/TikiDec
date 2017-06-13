package com.buddy.tiki.helper;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DatabaseHelper$$Lambda$1 implements RealmMigration {
    private static final DatabaseHelper$$Lambda$1 f544a = new DatabaseHelper$$Lambda$1();

    private DatabaseHelper$$Lambda$1() {
    }

    public static RealmMigration lambdaFactory$() {
        return a;
    }

    @Hidden
    public void migrate(DynamicRealm dynamicRealm, long j, long j2) {
        DatabaseHelper.m118a(dynamicRealm, j, j2);
    }
}
