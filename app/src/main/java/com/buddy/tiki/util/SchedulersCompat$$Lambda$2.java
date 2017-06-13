package com.buddy.tiki.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SchedulersCompat$$Lambda$2 implements ObservableTransformer {
    private static final SchedulersCompat$$Lambda$2 f2457a = new SchedulersCompat$$Lambda$2();

    private SchedulersCompat$$Lambda$2() {
    }

    public static ObservableTransformer lambdaFactory$() {
        return a;
    }

    @Hidden
    public ObservableSource apply(Observable observable) {
        return observable.subscribeOn(Schedulers.computation()).unsubscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }
}
