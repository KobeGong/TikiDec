package com.buddy.tiki.helper;

import com.buddy.tiki.model.resource.Gift;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DownloadHelper$$Lambda$1 implements Consumer {
    private static final DownloadHelper$$Lambda$1 f667a = new DownloadHelper$$Lambda$1();

    private DownloadHelper$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        DownloadHelper.m137d((Gift) obj);
    }
}
