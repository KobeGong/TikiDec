package com.buddy.tiki.helper;

import com.buddy.tiki.model.resource.Gift;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$4 implements Predicate {
    private static final DownloadHelper$$Lambda$4 f670a = new DownloadHelper$$Lambda$4();

    private DownloadHelper$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return DownloadHelper.m134b((Gift) obj);
    }
}
