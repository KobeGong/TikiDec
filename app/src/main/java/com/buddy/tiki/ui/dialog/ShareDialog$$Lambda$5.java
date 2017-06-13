package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ShareDialog$$Lambda$5 implements Consumer {
    private final ShareDialog f1987a;

    private ShareDialog$$Lambda$5(ShareDialog shareDialog) {
        this.a = shareDialog;
    }

    public static Consumer lambdaFactory$(ShareDialog shareDialog) {
        return new ShareDialog$$Lambda$5(shareDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1186a(obj);
    }
}
