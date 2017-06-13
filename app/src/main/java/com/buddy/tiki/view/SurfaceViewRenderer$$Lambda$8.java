package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SurfaceViewRenderer$$Lambda$8 implements Runnable {
    private final SurfaceViewRenderer f2831a;

    private SurfaceViewRenderer$$Lambda$8(SurfaceViewRenderer surfaceViewRenderer) {
        this.a = surfaceViewRenderer;
    }

    public static Runnable lambdaFactory$(SurfaceViewRenderer surfaceViewRenderer) {
        return new SurfaceViewRenderer$$Lambda$8(surfaceViewRenderer);
    }

    @Hidden
    public void run() {
        this.a.requestLayout();
    }
}
