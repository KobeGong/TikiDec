package com.buddy.tiki.helper;

import android.graphics.Bitmap;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$54 implements Consumer {
    private final Bitmap f634a;

    private DialogHelper$$Lambda$54(Bitmap bitmap) {
        this.a = bitmap;
    }

    public static Consumer lambdaFactory$(Bitmap bitmap) {
        return new DialogHelper$$Lambda$54(bitmap);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$null$12(this.a, (Throwable) obj);
    }
}
