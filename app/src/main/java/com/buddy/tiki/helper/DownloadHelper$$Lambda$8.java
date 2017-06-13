package com.buddy.tiki.helper;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$8 implements Function {
    private static final DownloadHelper$$Lambda$8 f674a = new DownloadHelper$$Lambda$8();

    private DownloadHelper$$Lambda$8() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return DownloadHelper.m123a((OperInfo) obj);
    }
}
