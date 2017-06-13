package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class KeyboardDialog$$Lambda$9 implements Consumer {
    private final KeyboardDialog f1933a;

    private KeyboardDialog$$Lambda$9(KeyboardDialog keyboardDialog) {
        this.a = keyboardDialog;
    }

    public static Consumer lambdaFactory$(KeyboardDialog keyboardDialog) {
        return new KeyboardDialog$$Lambda$9(keyboardDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1131d(obj);
    }
}
