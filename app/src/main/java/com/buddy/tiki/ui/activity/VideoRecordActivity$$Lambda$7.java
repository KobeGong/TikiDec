package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$7 implements Consumer {
    private final VideoRecordActivity f1511a;

    private VideoRecordActivity$$Lambda$7(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$7(videoRecordActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m903b((Long) obj);
    }
}
