package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.C03901;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$5 implements Runnable {
    private final String[] f744a;
    private final String f745b;

    private UploadHelper$1$$Lambda$5(String[] strArr, String str) {
        this.a = strArr;
        this.b = str;
    }

    public static Runnable lambdaFactory$(String[] strArr, String str) {
        return new UploadHelper$1$$Lambda$5(strArr, str);
    }

    @Hidden
    public void run() {
        C03901.m153a(this.a, this.b);
    }
}
