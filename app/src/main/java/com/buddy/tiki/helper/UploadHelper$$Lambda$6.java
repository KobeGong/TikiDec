package com.buddy.tiki.helper;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$6 implements Runnable {
    private final String[] f728a;

    private UploadHelper$$Lambda$6(String[] strArr) {
        this.a = strArr;
    }

    public static Runnable lambdaFactory$(String[] strArr) {
        return new UploadHelper$$Lambda$6(strArr);
    }

    @Hidden
    public void run() {
        UploadHelper.m172a(this.a);
    }
}
