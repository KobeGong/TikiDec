package com.buddy.tiki.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SchedulersCompat$$Lambda$4 implements ObservableTransformer {
    private static final SchedulersCompat$$Lambda$4 f2459a = new SchedulersCompat$$Lambda$4();

    private SchedulersCompat$$Lambda$4() {
    }

    public static ObservableTransformer lambdaFactory$() {
        return a;
    }

    @Hidden
    public ObservableSource apply(Observable observable) {
        return observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }
}
