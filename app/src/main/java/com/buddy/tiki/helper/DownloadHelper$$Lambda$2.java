package com.buddy.tiki.helper;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class DownloadHelper$$Lambda$2 implements Function {
    private static final DownloadHelper$$Lambda$2 f668a = new DownloadHelper$$Lambda$2();

    private DownloadHelper$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return Observable.fromIterable((List) obj);
    }
}
