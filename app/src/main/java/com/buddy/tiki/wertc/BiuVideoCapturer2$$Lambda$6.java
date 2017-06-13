package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.CountDownLatch;

final /* synthetic */ class BiuVideoCapturer2$$Lambda$6 implements Runnable {
    private final BiuVideoCapturer2 f3518a;
    private final CountDownLatch f3519b;

    private BiuVideoCapturer2$$Lambda$6(BiuVideoCapturer2 biuVideoCapturer2, CountDownLatch countDownLatch) {
        this.a = biuVideoCapturer2;
        this.b = countDownLatch;
    }

    public static Runnable lambdaFactory$(BiuVideoCapturer2 biuVideoCapturer2, CountDownLatch countDownLatch) {
        return new BiuVideoCapturer2$$Lambda$6(biuVideoCapturer2, countDownLatch);
    }

    @Hidden
    public void run() {
        this.a.m2186a(this.b);
    }
}
