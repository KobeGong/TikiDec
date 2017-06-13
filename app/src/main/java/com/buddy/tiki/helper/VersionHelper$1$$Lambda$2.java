package com.buddy.tiki.helper;

import com.buddy.tiki.helper.VersionHelper.C03911;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VersionHelper$1$$Lambda$2 implements Predicate {
    private static final VersionHelper$1$$Lambda$2 f762a = new VersionHelper$1$$Lambda$2();

    private VersionHelper$1$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return C03911.m179a((String) obj);
    }
}
