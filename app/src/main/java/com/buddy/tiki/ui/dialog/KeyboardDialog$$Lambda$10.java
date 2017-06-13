package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class KeyboardDialog$$Lambda$10 implements Consumer {
    private final KeyboardDialog f1922a;

    private KeyboardDialog$$Lambda$10(KeyboardDialog keyboardDialog) {
        this.a = keyboardDialog;
    }

    public static Consumer lambdaFactory$(KeyboardDialog keyboardDialog) {
        return new KeyboardDialog$$Lambda$10(keyboardDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1130c(obj);
    }
}
