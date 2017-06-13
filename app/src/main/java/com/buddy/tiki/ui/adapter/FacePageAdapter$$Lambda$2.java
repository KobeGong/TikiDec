package com.buddy.tiki.ui.adapter;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FacePageAdapter$$Lambda$2 implements Consumer {
    private static final FacePageAdapter$$Lambda$2 f1771a = new FacePageAdapter$$Lambda$2();

    private FacePageAdapter$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        FacePageAdapter.f1794f.m264e("something wrong", (Throwable) obj);
    }
}
