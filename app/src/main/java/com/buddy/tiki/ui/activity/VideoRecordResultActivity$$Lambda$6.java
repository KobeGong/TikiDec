package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$6 implements Consumer {
    private final VideoRecordResultActivity f1564a;

    private VideoRecordResultActivity$$Lambda$6(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$6(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m926c(obj);
    }
}
