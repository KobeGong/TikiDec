package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$7 implements Consumer {
    private final VideoRecordResultActivity f1565a;

    private VideoRecordResultActivity$$Lambda$7(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$7(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m925b(obj);
    }
}
