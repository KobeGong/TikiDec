package com.buddy.tiki.helper;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$3 implements Consumer {
    private final UploadHelper f715a;
    private final String[] f716b;

    private UploadHelper$$Lambda$3(UploadHelper uploadHelper, String[] strArr) {
        this.a = uploadHelper;
        this.b = strArr;
    }

    public static Consumer lambdaFactory$(UploadHelper uploadHelper, String[] strArr) {
        return new UploadHelper$$Lambda$3(uploadHelper, strArr);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m176a(this.b, (Throwable) obj);
    }
}
