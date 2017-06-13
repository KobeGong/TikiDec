package com.buddy.tiki.helper;

import io.realm.DynamicRealmObject;
import io.realm.RealmObjectSchema.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DatabaseHelper$$Lambda$2 implements Function {
    private static final DatabaseHelper$$Lambda$2 f545a = new DatabaseHelper$$Lambda$2();

    private DatabaseHelper$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public void apply(DynamicRealmObject dynamicRealmObject) {
        dynamicRealmObject.set("relation", Integer.valueOf(4));
    }
}
