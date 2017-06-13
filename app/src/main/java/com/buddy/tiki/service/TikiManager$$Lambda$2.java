package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TikiManager$$Lambda$2 implements Consumer {
    private static final TikiManager$$Lambda$2 f941a = new TikiManager$$Lambda$2();

    private TikiManager$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        TikiManager.f944a.m264e("log out", (Throwable) obj);
    }
}
