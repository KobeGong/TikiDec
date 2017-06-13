package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ShareDialog$$Lambda$4 implements Consumer {
    private final ShareDialog f1986a;

    private ShareDialog$$Lambda$4(ShareDialog shareDialog) {
        this.a = shareDialog;
    }

    public static Consumer lambdaFactory$(ShareDialog shareDialog) {
        return new ShareDialog$$Lambda$4(shareDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1187b(obj);
    }
}
