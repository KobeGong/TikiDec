package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class GiftDialog$$Lambda$1 implements Consumer {
    private final GiftDialog f1904a;

    private GiftDialog$$Lambda$1(GiftDialog giftDialog) {
        this.a = giftDialog;
    }

    public static Consumer lambdaFactory$(GiftDialog giftDialog) {
        return new GiftDialog$$Lambda$1(giftDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1124b(obj);
    }
}
