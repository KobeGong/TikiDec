package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BiuVideoCapturer2$$Lambda$7 implements Runnable {
    private final BiuVideoCapturer2 f3520a;
    private final byte[] f3521b;
    private final int f3522c;
    private final int f3523d;
    private final int f3524e;
    private final long f3525f;

    private BiuVideoCapturer2$$Lambda$7(BiuVideoCapturer2 biuVideoCapturer2, byte[] bArr, int i, int i2, int i3, long j) {
        this.a = biuVideoCapturer2;
        this.b = bArr;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = j;
    }

    public static Runnable lambdaFactory$(BiuVideoCapturer2 biuVideoCapturer2, byte[] bArr, int i, int i2, int i3, long j) {
        return new BiuVideoCapturer2$$Lambda$7(biuVideoCapturer2, bArr, i, i2, i3, j);
    }

    @Hidden
    public void run() {
        this.a.m2188a(this.b, this.c, this.d, this.e, this.f);
    }
}
