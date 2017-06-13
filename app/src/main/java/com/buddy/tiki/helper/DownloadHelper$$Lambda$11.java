package com.buddy.tiki.helper;

import com.buddy.tiki.model.resource.FaceUnity;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$11 implements Predicate {
    private static final DownloadHelper$$Lambda$11 f659a = new DownloadHelper$$Lambda$11();

    private DownloadHelper$$Lambda$11() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return DownloadHelper.m133b((FaceUnity) obj);
    }
}
