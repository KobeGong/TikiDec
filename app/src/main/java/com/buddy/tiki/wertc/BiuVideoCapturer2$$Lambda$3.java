package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BiuVideoCapturer2$$Lambda$3 implements Runnable {
    private final BiuVideoCapturer2 f3506a;
    private final int f3507b;
    private final int f3508c;
    private final int f3509d;

    private BiuVideoCapturer2$$Lambda$3(BiuVideoCapturer2 biuVideoCapturer2, int i, int i2, int i3) {
        this.a = biuVideoCapturer2;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static Runnable lambdaFactory$(BiuVideoCapturer2 biuVideoCapturer2, int i, int i2, int i3) {
        return new BiuVideoCapturer2$$Lambda$3(biuVideoCapturer2, i, i2, i3);
    }

    @Hidden
    public void run() {
        this.a.m2190c(this.b, this.c, this.d);
    }
}
