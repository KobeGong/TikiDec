package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoMessageActivity$$Lambda$10 implements Consumer {
    private final VideoMessageActivity f1472a;

    private VideoMessageActivity$$Lambda$10(VideoMessageActivity videoMessageActivity) {
        this.a = videoMessageActivity;
    }

    public static Consumer lambdaFactory$(VideoMessageActivity videoMessageActivity) {
        return new VideoMessageActivity$$Lambda$10(videoMessageActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m845a((OperInfo) obj);
    }
}
