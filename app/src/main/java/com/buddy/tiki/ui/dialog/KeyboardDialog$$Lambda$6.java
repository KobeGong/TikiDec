package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class KeyboardDialog$$Lambda$6 implements Consumer {
    private final KeyboardDialog f1930a;

    private KeyboardDialog$$Lambda$6(KeyboardDialog keyboardDialog) {
        this.a = keyboardDialog;
    }

    public static Consumer lambdaFactory$(KeyboardDialog keyboardDialog) {
        return new KeyboardDialog$$Lambda$6(keyboardDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1134g(obj);
    }
}
