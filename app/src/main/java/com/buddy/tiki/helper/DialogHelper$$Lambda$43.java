package com.buddy.tiki.helper;

import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$43 implements Predicate {
    private static final DialogHelper$$Lambda$43 f615a = new DialogHelper$$Lambda$43();

    private DialogHelper$$Lambda$43() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return ((Boolean) obj).booleanValue();
    }
}
