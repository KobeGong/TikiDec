package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;

final /* synthetic */ class BiuVideoCapturer2$$Lambda$1 implements Runnable {
    private final BiuVideoCapturer2 f3500a;
    private final CameraSwitchHandler f3501b;

    private BiuVideoCapturer2$$Lambda$1(BiuVideoCapturer2 biuVideoCapturer2, CameraSwitchHandler cameraSwitchHandler) {
        this.a = biuVideoCapturer2;
        this.b = cameraSwitchHandler;
    }

    public static Runnable lambdaFactory$(BiuVideoCapturer2 biuVideoCapturer2, CameraSwitchHandler cameraSwitchHandler) {
        return new BiuVideoCapturer2$$Lambda$1(biuVideoCapturer2, cameraSwitchHandler);
    }

    @Hidden
    public void run() {
        this.a.m2187a(this.b);
    }
}
