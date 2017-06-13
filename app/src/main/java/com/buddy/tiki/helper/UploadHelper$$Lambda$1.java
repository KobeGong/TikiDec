package com.buddy.tiki.helper;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.Callable;

final /* synthetic */ class UploadHelper$$Lambda$1 implements Callable {
    private final String f707a;
    private final String f708b;

    private UploadHelper$$Lambda$1(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static Callable lambdaFactory$(String str, String str2) {
        return new UploadHelper$$Lambda$1(str, str2);
    }

    @Hidden
    public Object call() {
        return UploadHelper.m165a(this.a, this.b);
    }
}
