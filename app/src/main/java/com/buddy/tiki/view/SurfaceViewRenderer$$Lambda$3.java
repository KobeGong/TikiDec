package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$3 implements Runnable {
    private final SurfaceViewRenderer f2825a;

    private SurfaceViewRenderer$$Lambda$3(SurfaceViewRenderer surfaceViewRenderer) {
        this.a = surfaceViewRenderer;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer) {
        return new SurfaceViewRenderer$$Lambda$3(surfaceViewRenderer);
    }

    @Hidden
    public void run() {
        this.a.m1771c();
    }
}
