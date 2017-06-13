package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class GiftDialog$$Lambda$3 implements Consumer {
    private final GiftDialog f1906a;

    private GiftDialog$$Lambda$3(GiftDialog giftDialog) {
        this.a = giftDialog;
    }

    public static Consumer lambdaFactory$(GiftDialog giftDialog) {
        return new GiftDialog$$Lambda$3(giftDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1120a((ConfigInfo) obj);
    }
}
