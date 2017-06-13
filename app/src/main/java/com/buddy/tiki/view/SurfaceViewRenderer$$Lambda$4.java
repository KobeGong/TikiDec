package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$4 implements Runnable {
    private final SurfaceViewRenderer f2826a;

    private SurfaceViewRenderer$$Lambda$4(SurfaceViewRenderer surfaceViewRenderer) {
        this.a = surfaceViewRenderer;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer) {
        return new SurfaceViewRenderer$$Lambda$4(surfaceViewRenderer);
    }

    @Hidden
    public void run() {
        this.a.m1775e();
    }
}
