package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ShareDialog$$Lambda$2 implements Consumer {
    private final ShareDialog f1984a;

    private ShareDialog$$Lambda$2(ShareDialog shareDialog) {
        this.a = shareDialog;
    }

    public static Consumer lambdaFactory$(ShareDialog shareDialog) {
        return new ShareDialog$$Lambda$2(shareDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1189d(obj);
    }
}
