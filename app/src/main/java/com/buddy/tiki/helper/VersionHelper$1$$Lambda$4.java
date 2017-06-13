package com.buddy.tiki.helper;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VersionHelper$1$$Lambda$4 implements Consumer {
    private static final VersionHelper$1$$Lambda$4 f764a = new VersionHelper$1$$Lambda$4();

    private VersionHelper$1$$Lambda$4() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        VersionHelper.f766a.m264e("load download ", (Throwable) obj);
    }
}
