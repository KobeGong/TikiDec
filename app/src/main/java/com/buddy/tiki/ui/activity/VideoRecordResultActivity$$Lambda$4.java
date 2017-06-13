package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordResultActivity$$Lambda$4 implements Consumer {
    private final VideoRecordResultActivity f1562a;

    private VideoRecordResultActivity$$Lambda$4(VideoRecordResultActivity videoRecordResultActivity) {
        this.a = videoRecordResultActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordResultActivity videoRecordResultActivity) {
        return new VideoRecordResultActivity$$Lambda$4(videoRecordResultActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m928e(obj);
    }
}
