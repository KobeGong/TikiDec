package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$5 implements Consumer {
    private final VideoRecordResultActivity f1563a;

    private VideoRecordResultActivity$$Lambda$5(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$5(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m927d(obj);
    }
}
