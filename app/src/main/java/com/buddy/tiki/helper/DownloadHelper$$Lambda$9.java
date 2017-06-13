package com.buddy.tiki.helper;

import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class DownloadHelper$$Lambda$9 implements Predicate {
    private static final DownloadHelper$$Lambda$9 f675a = new DownloadHelper$$Lambda$9();

    private DownloadHelper$$Lambda$9() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return DownloadHelper.m130a((List) obj);
    }
}
