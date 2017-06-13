package com.buddy.tiki.util;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchStatisticsUtil$$Lambda$3 implements Consumer {
    private static final MatchStatisticsUtil$$Lambda$3 f2420a = new MatchStatisticsUtil$$Lambda$3();

    private MatchStatisticsUtil$$Lambda$3() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        MatchStatisticsUtil.f2423a.m264e("reportMatch", (Throwable) obj);
    }
}
