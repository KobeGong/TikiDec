package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$11 implements Consumer {
    private final VideoRecordActivity f1499a;

    private VideoRecordActivity$$Lambda$11(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$11(videoRecordActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m899a((Throwable) obj);
    }
}
