package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$2 implements Consumer {
    private final VideoRecordResultActivity f1560a;

    private VideoRecordResultActivity$$Lambda$2(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$2(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m930g(obj);
    }
}
