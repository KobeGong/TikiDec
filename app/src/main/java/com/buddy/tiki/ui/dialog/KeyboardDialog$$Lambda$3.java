package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class KeyboardDialog$$Lambda$3 implements Consumer {
    private final KeyboardDialog f1927a;

    private KeyboardDialog$$Lambda$3(KeyboardDialog keyboardDialog) {
        this.a = keyboardDialog;
    }

    public static Consumer lambdaFactory$(KeyboardDialog keyboardDialog) {
        return new KeyboardDialog$$Lambda$3(keyboardDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1137j(obj);
    }
}
