package com.buddy.tiki.im;

import com.buddy.tiki.im.TopIM.C03991;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TopIM$1$$Lambda$1 implements Runnable {
    private final C03991 f861a;

    private TopIM$1$$Lambda$1(C03991 c03991) {
        this.a = c03991;
    }

    public static Runnable lambdaFactory$(C03991 c03991) {
        return new TopIM$1$$Lambda$1(c03991);
    }

    @Hidden
    public void run() {
        this.a.m246b();
    }
}
