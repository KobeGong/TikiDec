package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoFilterPlayer$$Lambda$1 implements Runnable {
    private final VideoFilterPlayer f2899a;

    private VideoFilterPlayer$$Lambda$1(VideoFilterPlayer videoFilterPlayer) {
        this.a = videoFilterPlayer;
    }

    public static Runnable lambdaFactory$(VideoFilterPlayer videoFilterPlayer) {
        return new VideoFilterPlayer$$Lambda$1(videoFilterPlayer);
    }

    @Hidden
    public void run() {
        this.a.m1814b();
    }
}
