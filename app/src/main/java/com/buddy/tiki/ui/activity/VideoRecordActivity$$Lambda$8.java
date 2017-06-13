package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import org.bytedeco.javacpp.Pointer;

final /* synthetic */ class VideoRecordActivity$$Lambda$8 implements Consumer {
    private static final VideoRecordActivity$$Lambda$8 f1512a = new VideoRecordActivity$$Lambda$8();

    private VideoRecordActivity$$Lambda$8() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        VideoRecordActivity.f1525d.m263e("Time[" + ((Long) obj) + "]:" + Pointer.physicalBytes());
    }
}
