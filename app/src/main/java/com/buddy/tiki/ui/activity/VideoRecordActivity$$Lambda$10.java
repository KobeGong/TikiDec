package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$10 implements Consumer {
    private final VideoRecordActivity f1498a;

    private VideoRecordActivity$$Lambda$10(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$10(videoRecordActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m897a((Integer) obj);
    }
}
