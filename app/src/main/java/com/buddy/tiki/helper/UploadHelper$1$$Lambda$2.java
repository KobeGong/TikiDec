package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.UploadCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$2 implements Runnable {
    private final UploadCallback f740a;

    private UploadHelper$1$$Lambda$2(UploadCallback uploadCallback) {
        this.a = uploadCallback;
    }

    public static Runnable lambdaFactory$(UploadCallback uploadCallback) {
        return new UploadHelper$1$$Lambda$2(uploadCallback);
    }

    @Hidden
    public void run() {
        this.a.onStart();
    }
}
