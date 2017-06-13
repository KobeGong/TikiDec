package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.C03901;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$1 implements Runnable {
    private final String[] f739a;

    private UploadHelper$1$$Lambda$1(String[] strArr) {
        this.a = strArr;
    }

    public static Runnable lambdaFactory$(String[] strArr) {
        return new UploadHelper$1$$Lambda$1(strArr);
    }

    @Hidden
    public void run() {
        C03901.m158b(this.a);
    }
}
