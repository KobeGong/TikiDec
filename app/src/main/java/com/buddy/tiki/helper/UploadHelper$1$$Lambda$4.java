package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.UploadCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$1$$Lambda$4 implements Runnable {
    private final UploadCallback f742a;
    private final String f743b;

    private UploadHelper$1$$Lambda$4(UploadCallback uploadCallback, String str) {
        this.a = uploadCallback;
        this.b = str;
    }

    public static Runnable lambdaFactory$(UploadCallback uploadCallback, String str) {
        return new UploadHelper$1$$Lambda$4(uploadCallback, str);
    }

    @Hidden
    public void run() {
        this.a.uploadProgress(this.b, 0, 0, -1.0f, 0);
    }
}
