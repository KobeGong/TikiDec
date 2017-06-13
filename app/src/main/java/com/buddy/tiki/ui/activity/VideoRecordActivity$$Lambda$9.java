package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$9 implements Consumer {
    private final VideoRecordActivity f1513a;

    private VideoRecordActivity$$Lambda$9(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$9(videoRecordActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m902b((Integer) obj);
    }
}
