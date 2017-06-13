package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$5 implements Consumer {
    private final VideoRecordActivity f1509a;

    private VideoRecordActivity$$Lambda$5(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$5(videoRecordActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m904b(obj);
    }
}
