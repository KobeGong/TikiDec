package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.CountDownLatch;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$6 implements Runnable {
    private final SurfaceViewRenderer f2828a;
    private final CountDownLatch f2829b;

    private SurfaceViewRenderer$$Lambda$6(SurfaceViewRenderer surfaceViewRenderer, CountDownLatch countDownLatch) {
        this.a = surfaceViewRenderer;
        this.b = countDownLatch;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer, CountDownLatch countDownLatch) {
        return new SurfaceViewRenderer$$Lambda$6(surfaceViewRenderer, countDownLatch);
    }

    @Hidden
    public void run() {
        this.a.m1780a(this.b);
    }
}
