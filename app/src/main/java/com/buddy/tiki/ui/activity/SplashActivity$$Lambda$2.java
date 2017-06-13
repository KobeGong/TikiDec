package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SplashActivity$$Lambda$2 implements Consumer {
    private final SplashActivity f1461a;

    private SplashActivity$$Lambda$2(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    public static Consumer lambdaFactory$(SplashActivity splashActivity) {
        return new SplashActivity$$Lambda$2(splashActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m815c((Throwable) obj);
    }
}
