package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.VideoRecordActivity.C04431;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$1$$Lambda$1 implements Consumer {
    private final C04431 f1514a;

    private VideoRecordActivity$1$$Lambda$1(C04431 c04431) {
        this.a = c04431;
    }

    public static Consumer lambdaFactory$(C04431 c04431) {
        return new VideoRecordActivity$1$$Lambda$1(c04431);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m854a((Long) obj);
    }
}
