package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BiuVideoCapturer2$$Lambda$2 implements Runnable {
    private final BiuVideoCapturer2 f3502a;
    private final int f3503b;
    private final int f3504c;
    private final int f3505d;

    private BiuVideoCapturer2$$Lambda$2(BiuVideoCapturer2 biuVideoCapturer2, int i, int i2, int i3) {
        this.a = biuVideoCapturer2;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static Runnable lambdaFactory$(BiuVideoCapturer2 biuVideoCapturer2, int i, int i2, int i3) {
        return new BiuVideoCapturer2$$Lambda$2(biuVideoCapturer2, i, i2, i3);
    }

    @Hidden
    public void run() {
        this.a.m2191d(this.b, this.c, this.d);
    }
}
