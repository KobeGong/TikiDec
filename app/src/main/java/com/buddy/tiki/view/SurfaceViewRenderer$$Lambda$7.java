package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$7 implements Runnable {
    private final SurfaceViewRenderer f2830a;

    private SurfaceViewRenderer$$Lambda$7(SurfaceViewRenderer surfaceViewRenderer) {
        this.a = surfaceViewRenderer;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer) {
        return new SurfaceViewRenderer$$Lambda$7(surfaceViewRenderer);
    }

    @Hidden
    public void run() {
        this.a.m1779a();
    }
}
