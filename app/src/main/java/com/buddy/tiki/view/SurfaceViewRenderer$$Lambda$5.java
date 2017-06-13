package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$5 implements Runnable {
    private final SurfaceViewRenderer f2827a;

    private SurfaceViewRenderer$$Lambda$5(SurfaceViewRenderer surfaceViewRenderer) {
        this.a = surfaceViewRenderer;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer) {
        return new SurfaceViewRenderer$$Lambda$5(surfaceViewRenderer);
    }

    @Hidden
    public void run() {
        this.a.m1781b();
    }
}
