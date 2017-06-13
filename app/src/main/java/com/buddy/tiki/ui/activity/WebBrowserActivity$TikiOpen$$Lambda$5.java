package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$TikiOpen$$Lambda$5 implements Consumer {
    private static final WebBrowserActivity$TikiOpen$$Lambda$5 f1603a = new WebBrowserActivity$TikiOpen$$Lambda$5();

    private WebBrowserActivity$TikiOpen$$Lambda$5() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        WebBrowserActivity.f1607a.m264e("oauth: ", (Throwable) obj);
    }
}
