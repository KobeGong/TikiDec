package com.buddy.tiki.service.base;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class Foreground$$Lambda$7 implements Runnable {
    private final Foreground f1041a;

    private Foreground$$Lambda$7(Foreground foreground) {
        this.a = foreground;
    }

    public static Runnable lambdaFactory$(Foreground foreground) {
        return new Foreground$$Lambda$7(foreground);
    }

    @Hidden
    public void run() {
        this.a.m399a();
    }
}
