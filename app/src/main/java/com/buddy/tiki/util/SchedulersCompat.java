package com.buddy.tiki.util;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;

public class SchedulersCompat {
    private static final ObservableTransformer f2461a = SchedulersCompat$$Lambda$2.lambdaFactory$();
    private static final ObservableTransformer f2462b = SchedulersCompat$$Lambda$3.lambdaFactory$();
    private static final ObservableTransformer f2463c = SchedulersCompat$$Lambda$4.lambdaFactory$();
    private static final ObservableTransformer f2464d = SchedulersCompat$$Lambda$5.lambdaFactory$();

    public static <T> ObservableTransformer<T, T> applyComputationSchedulers() {
        return f2461a;
    }

    public static <T> ObservableTransformer<T, T> applyIoSchedulers() {
        return f2462b;
    }

    public static <T> ObservableTransformer<T, T> applyNewSchedulers() {
        return f2463c;
    }

    public static <T> ObservableTransformer<T, T> applyTrampolineSchedulers() {
        return f2464d;
    }

    public static CompletableTransformer applyIOCompletableSchedulers() {
        return SchedulersCompat$$Lambda$1.lambdaFactory$();
    }
}
