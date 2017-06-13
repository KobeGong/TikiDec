package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoMessageActivity$$Lambda$6 implements Consumer {
    private final VideoMessageActivity f1478a;

    private VideoMessageActivity$$Lambda$6(VideoMessageActivity videoMessageActivity) {
        this.a = videoMessageActivity;
    }

    public static Consumer lambdaFactory$(VideoMessageActivity videoMessageActivity) {
        return new VideoMessageActivity$$Lambda$6(videoMessageActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m846a((Boolean) obj);
    }
}
