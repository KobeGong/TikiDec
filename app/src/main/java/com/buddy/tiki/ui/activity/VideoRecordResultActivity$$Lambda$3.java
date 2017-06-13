package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$3 implements Consumer {
    private final VideoRecordResultActivity f1561a;

    private VideoRecordResultActivity$$Lambda$3(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$3(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m929f(obj);
    }
}
