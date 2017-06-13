package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class GiftDialog$$Lambda$7 implements Consumer {
    private final GiftDialog f1910a;

    private GiftDialog$$Lambda$7(GiftDialog giftDialog) {
        this.a = giftDialog;
    }

    public static Consumer lambdaFactory$(GiftDialog giftDialog) {
        return new GiftDialog$$Lambda$7(giftDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1123a((List) obj);
    }
}
