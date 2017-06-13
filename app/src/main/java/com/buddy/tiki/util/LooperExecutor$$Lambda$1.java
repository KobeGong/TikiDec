package com.buddy.tiki.util;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LooperExecutor$$Lambda$1 implements Runnable {
    private final LooperExecutor f2408a;

    private LooperExecutor$$Lambda$1(LooperExecutor looperExecutor) {
        this.a = looperExecutor;
    }

    public static Runnable lambdaFactory$(LooperExecutor looperExecutor) {
        return new LooperExecutor$$Lambda$1(looperExecutor);
    }

    @Hidden
    public void run() {
        this.a.m1552a();
    }
}
