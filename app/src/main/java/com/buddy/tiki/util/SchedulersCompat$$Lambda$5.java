package com.buddy.tiki.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SchedulersCompat$$Lambda$5 implements ObservableTransformer {
    private static final SchedulersCompat$$Lambda$5 f2460a = new SchedulersCompat$$Lambda$5();

    private SchedulersCompat$$Lambda$5() {
    }

    public static ObservableTransformer lambdaFactory$() {
        return a;
    }

    @Hidden
    public ObservableSource apply(Observable observable) {
        return observable.subscribeOn(Schedulers.trampoline()).observeOn(AndroidSchedulers.mainThread());
    }
}
