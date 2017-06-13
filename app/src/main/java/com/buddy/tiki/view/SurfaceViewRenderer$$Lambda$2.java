package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$2 implements Runnable {
    private final SurfaceViewRenderer f2824a;

    private SurfaceViewRenderer$$Lambda$2(SurfaceViewRenderer surfaceViewRenderer) {
        this.a = surfaceViewRenderer;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer) {
        return new SurfaceViewRenderer$$Lambda$2(surfaceViewRenderer);
    }

    @Hidden
    public void run() {
        this.a.m1775e();
    }
}
