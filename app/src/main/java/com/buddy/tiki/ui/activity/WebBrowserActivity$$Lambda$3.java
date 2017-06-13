package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$$Lambda$3 implements Consumer {
    private final WebBrowserActivity f1587a;

    private WebBrowserActivity$$Lambda$3(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public static Consumer lambdaFactory$(WebBrowserActivity webBrowserActivity) {
        return new WebBrowserActivity$$Lambda$3(webBrowserActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m960a(obj);
    }
}
