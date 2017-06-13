package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoMessageActivity$$Lambda$8 implements Consumer {
    private final VideoMessageActivity f1480a;

    private VideoMessageActivity$$Lambda$8(VideoMessageActivity videoMessageActivity) {
        this.a = videoMessageActivity;
    }

    public static Consumer lambdaFactory$(VideoMessageActivity videoMessageActivity) {
        return new VideoMessageActivity$$Lambda$8(videoMessageActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m844a((ConfigInfo) obj);
    }
}
