package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BiuVideoCapturer2$$Lambda$5 implements Runnable {
    private final BiuVideoCapturer2 f3514a;
    private final int f3515b;
    private final int f3516c;
    private final int f3517d;

    private BiuVideoCapturer2$$Lambda$5(BiuVideoCapturer2 biuVideoCapturer2, int i, int i2, int i3) {
        this.a = biuVideoCapturer2;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static Runnable lambdaFactory$(BiuVideoCapturer2 biuVideoCapturer2, int i, int i2, int i3) {
        return new BiuVideoCapturer2$$Lambda$5(biuVideoCapturer2, i, i2, i3);
    }

    @Hidden
    public void run() {
        this.a.m2185a(this.b, this.c, this.d);
    }
}
