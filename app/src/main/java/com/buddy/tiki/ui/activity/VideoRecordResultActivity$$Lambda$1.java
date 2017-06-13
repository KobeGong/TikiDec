package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$1 implements Consumer {
    private final VideoRecordResultActivity f1559a;

    private VideoRecordResultActivity$$Lambda$1(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$1(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m931h(obj);
    }
}
