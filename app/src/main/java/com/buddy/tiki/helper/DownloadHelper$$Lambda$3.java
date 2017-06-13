package com.buddy.tiki.helper;

import com.buddy.tiki.model.resource.Gift;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$3 implements Predicate {
    private static final DownloadHelper$$Lambda$3 f669a = new DownloadHelper$$Lambda$3();

    private DownloadHelper$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return DownloadHelper.m136c((Gift) obj);
    }
}
