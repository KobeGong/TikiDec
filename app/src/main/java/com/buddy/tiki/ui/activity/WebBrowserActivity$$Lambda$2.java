package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$$Lambda$2 implements Consumer {
    private final WebBrowserActivity f1586a;

    private WebBrowserActivity$$Lambda$2(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public static Consumer lambdaFactory$(WebBrowserActivity webBrowserActivity) {
        return new WebBrowserActivity$$Lambda$2(webBrowserActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m962b(obj);
    }
}
