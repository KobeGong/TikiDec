package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class KeyboardDialog$$Lambda$1 implements Consumer {
    private final KeyboardDialog f1925a;

    private KeyboardDialog$$Lambda$1(KeyboardDialog keyboardDialog) {
        this.a = keyboardDialog;
    }

    public static Consumer lambdaFactory$(KeyboardDialog keyboardDialog) {
        return new KeyboardDialog$$Lambda$1(keyboardDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1139l(obj);
    }
}
