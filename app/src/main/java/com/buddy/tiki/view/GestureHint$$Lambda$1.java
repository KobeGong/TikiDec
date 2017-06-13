package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class GestureHint$$Lambda$1 implements Consumer {
    private final GestureHint f2654a;

    private GestureHint$$Lambda$1(GestureHint gestureHint) {
        this.a = gestureHint;
    }

    public static Consumer lambdaFactory$(GestureHint gestureHint) {
        return new GestureHint$$Lambda$1(gestureHint);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1701a(obj);
    }
}
