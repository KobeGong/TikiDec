package com.buddy.tiki.service.base;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BaseManager$HttpResultFunction$$Lambda$1 implements CompletableSource {
    private static final BaseManager$HttpResultFunction$$Lambda$1 f1022a = new BaseManager$HttpResultFunction$$Lambda$1();

    private BaseManager$HttpResultFunction$$Lambda$1() {
    }

    public static CompletableSource lambdaFactory$() {
        return a;
    }

    @Hidden
    public void subscribe(CompletableObserver completableObserver) {
        completableObserver.onComplete();
    }
}
