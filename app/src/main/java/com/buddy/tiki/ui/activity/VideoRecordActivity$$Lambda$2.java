package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$2 implements Consumer {
    private final VideoRecordActivity f1506a;

    private VideoRecordActivity$$Lambda$2(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$2(videoRecordActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m907e(obj);
    }
}
