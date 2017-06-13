package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DoubleLayerImageView$$Lambda$1 implements Runnable {
    private final DoubleLayerImageView f2632a;
    private final boolean f2633b;

    private DoubleLayerImageView$$Lambda$1(DoubleLayerImageView doubleLayerImageView, boolean z) {
        this.a = doubleLayerImageView;
        this.b = z;
    }

    public static Runnable lambdaFactory$(DoubleLayerImageView doubleLayerImageView, boolean z) {
        return new DoubleLayerImageView$$Lambda$1(doubleLayerImageView, z);
    }

    @Hidden
    public void run() {
        this.a.m1692a(this.b);
    }
}
