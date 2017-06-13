package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class KeyboardDialog$$Lambda$5 implements Consumer {
    private final KeyboardDialog f1929a;

    private KeyboardDialog$$Lambda$5(KeyboardDialog keyboardDialog) {
        this.a = keyboardDialog;
    }

    public static Consumer lambdaFactory$(KeyboardDialog keyboardDialog) {
        return new KeyboardDialog$$Lambda$5(keyboardDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1135h(obj);
    }
}
