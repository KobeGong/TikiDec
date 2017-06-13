package com.buddy.tiki.helper;

import android.graphics.Bitmap;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$51 implements Consumer {
    private final Bitmap f629a;

    private DialogHelper$$Lambda$51(Bitmap bitmap) {
        this.a = bitmap;
    }

    public static Consumer lambdaFactory$(Bitmap bitmap) {
        return new DialogHelper$$Lambda$51(bitmap);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$null$9(this.a, (byte[]) obj);
    }
}
