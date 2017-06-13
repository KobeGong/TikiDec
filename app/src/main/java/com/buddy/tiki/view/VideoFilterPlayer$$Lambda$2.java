package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoFilterPlayer$$Lambda$2 implements Runnable {
    private final VideoFilterPlayer f2900a;

    private VideoFilterPlayer$$Lambda$2(VideoFilterPlayer videoFilterPlayer) {
        this.a = videoFilterPlayer;
    }

    public static Runnable lambdaFactory$(VideoFilterPlayer videoFilterPlayer) {
        return new VideoFilterPlayer$$Lambda$2(videoFilterPlayer);
    }

    @Hidden
    public void run() {
        this.a.m1812a();
    }
}
