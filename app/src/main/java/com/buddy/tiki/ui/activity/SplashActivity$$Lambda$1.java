package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SplashActivity$$Lambda$1 implements Consumer {
    private final SplashActivity f1460a;

    private SplashActivity$$Lambda$1(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    public static Consumer lambdaFactory$(SplashActivity splashActivity) {
        return new SplashActivity$$Lambda$1(splashActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m791a((ConfigInfo) obj);
    }
}
