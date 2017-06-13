package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.UploadCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$6 implements Runnable {
    private final UploadCallback f746a;
    private final String f747b;

    private UploadHelper$1$$Lambda$6(UploadCallback uploadCallback, String str) {
        this.a = uploadCallback;
        this.b = str;
    }

    public static Runnable lambdaFactory$(UploadCallback uploadCallback, String str) {
        return new UploadHelper$1$$Lambda$6(uploadCallback, str);
    }

    @Hidden
    public void run() {
        this.a.uploadProgress(this.b, 0, 0, 100.0f, 0);
    }
}
