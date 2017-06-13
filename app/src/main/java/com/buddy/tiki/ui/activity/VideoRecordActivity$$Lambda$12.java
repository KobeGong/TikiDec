package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$12 implements Consumer {
    private final VideoRecordActivity f1500a;
    private final byte[] f1501b;
    private final int f1502c;
    private final int f1503d;

    private VideoRecordActivity$$Lambda$12(VideoRecordActivity videoRecordActivity, byte[] bArr, int i, int i2) {
        this.a = videoRecordActivity;
        this.b = bArr;
        this.c = i;
        this.d = i2;
    }

    public static Consumer lambdaFactory$(VideoRecordActivity videoRecordActivity, byte[] bArr, int i, int i2) {
        return new VideoRecordActivity$$Lambda$12(videoRecordActivity, bArr, i, i2);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m900a(this.b, this.c, this.d, (Integer) obj);
    }
}
