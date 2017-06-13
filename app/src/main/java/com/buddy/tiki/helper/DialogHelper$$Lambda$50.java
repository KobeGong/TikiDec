package com.buddy.tiki.helper;

import android.graphics.Bitmap;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.Callable;

final /* synthetic */ class DialogHelper$$Lambda$50 implements Callable {
    private final Bitmap f628a;

    private DialogHelper$$Lambda$50(Bitmap bitmap) {
        this.a = bitmap;
    }

    public static Callable lambdaFactory$(Bitmap bitmap) {
        return new DialogHelper$$Lambda$50(bitmap);
    }

    @Hidden
    public Object call() {
        return DialogHelper.lambda$null$8(this.a);
    }
}
