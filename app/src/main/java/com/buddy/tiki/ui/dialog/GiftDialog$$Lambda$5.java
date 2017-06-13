package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class GiftDialog$$Lambda$5 implements Consumer {
    private final GiftDialog f1908a;

    private GiftDialog$$Lambda$5(GiftDialog giftDialog) {
        this.a = giftDialog;
    }

    public static Consumer lambdaFactory$(GiftDialog giftDialog) {
        return new GiftDialog$$Lambda$5(giftDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1121a((User) obj);
    }
}
