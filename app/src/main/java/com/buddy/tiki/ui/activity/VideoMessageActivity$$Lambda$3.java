package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoMessageActivity$$Lambda$3 implements Consumer {
    private final VideoMessageActivity f1475a;

    private VideoMessageActivity$$Lambda$3(VideoMessageActivity videoMessageActivity) {
        this.a = videoMessageActivity;
    }

    public static Consumer lambdaFactory$(VideoMessageActivity videoMessageActivity) {
        return new VideoMessageActivity$$Lambda$3(videoMessageActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m852c(obj);
    }
}
