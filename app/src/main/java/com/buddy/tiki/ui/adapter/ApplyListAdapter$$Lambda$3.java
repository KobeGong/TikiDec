package com.buddy.tiki.ui.adapter;

import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListAdapter$$Lambda$3 implements Predicate {
    private static final ApplyListAdapter$$Lambda$3 f1650a = new ApplyListAdapter$$Lambda$3();

    private ApplyListAdapter$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return ((Boolean) obj).booleanValue();
    }
}
