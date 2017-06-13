package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoMessageActivity$$Lambda$7 implements Consumer {
    private final VideoMessageActivity f1479a;

    private VideoMessageActivity$$Lambda$7(VideoMessageActivity videoMessageActivity) {
        this.a = videoMessageActivity;
    }

    public static Consumer lambdaFactory$(VideoMessageActivity videoMessageActivity) {
        return new VideoMessageActivity$$Lambda$7(videoMessageActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m849a((Throwable) obj);
    }
}
