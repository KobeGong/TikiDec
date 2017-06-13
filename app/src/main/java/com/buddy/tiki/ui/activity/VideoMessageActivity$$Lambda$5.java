package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoMessageActivity$$Lambda$5 implements Consumer {
    private final VideoMessageActivity f1477a;

    private VideoMessageActivity$$Lambda$5(VideoMessageActivity videoMessageActivity) {
        this.a = videoMessageActivity;
    }

    public static Consumer lambdaFactory$(VideoMessageActivity videoMessageActivity) {
        return new VideoMessageActivity$$Lambda$5(videoMessageActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m847a(obj);
    }
}
