package com.buddy.tiki.helper;

import com.buddy.tiki.helper.UploadHelper.UploadCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UploadHelper$$Lambda$7 implements Runnable {
    private final UploadCallback f729a;
    private final String f730b;

    private UploadHelper$$Lambda$7(UploadCallback uploadCallback, String str) {
        this.a = uploadCallback;
        this.b = str;
    }

    public static Runnable lambdaFactory$(UploadCallback uploadCallback, String str) {
        return new UploadHelper$$Lambda$7(uploadCallback, str);
    }

    @Hidden
    public void run() {
        this.a.uploadProgress(this.b, 0, 0, -1.0f, 0);
    }
}
