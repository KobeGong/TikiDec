package com.buddy.tiki.util;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SchedulersCompat$$Lambda$1 implements CompletableTransformer {
    private static final SchedulersCompat$$Lambda$1 f2456a = new SchedulersCompat$$Lambda$1();

    private SchedulersCompat$$Lambda$1() {
    }

    public static CompletableTransformer lambdaFactory$() {
        return a;
    }

    @Hidden
    public CompletableSource apply(Completable completable) {
        return completable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
