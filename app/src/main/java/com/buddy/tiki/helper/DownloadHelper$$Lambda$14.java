package com.buddy.tiki.helper;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$14 implements Consumer {
    private static final DownloadHelper$$Lambda$14 f662a = new DownloadHelper$$Lambda$14();

    private DownloadHelper$$Lambda$14() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        DownloadHelper.f677a.m264e("download faceunity error", (Throwable) obj);
    }
}
