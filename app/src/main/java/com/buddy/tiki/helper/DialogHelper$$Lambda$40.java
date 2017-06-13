package com.buddy.tiki.helper;

import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$40 implements Predicate {
    private static final DialogHelper$$Lambda$40 f612a = new DialogHelper$$Lambda$40();

    private DialogHelper$$Lambda$40() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return ((Boolean) obj).booleanValue();
    }
}
