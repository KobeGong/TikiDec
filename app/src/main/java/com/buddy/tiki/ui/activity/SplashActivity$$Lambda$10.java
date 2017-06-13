package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SplashActivity$$Lambda$10 implements Runnable {
    private final SplashActivity f1459a;

    private SplashActivity$$Lambda$10(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    public static Runnable lambdaFactory$(SplashActivity splashActivity) {
        return new SplashActivity$$Lambda$10(splashActivity);
    }

    @Hidden
    public void run() {
        this.a.m814c();
    }
}
