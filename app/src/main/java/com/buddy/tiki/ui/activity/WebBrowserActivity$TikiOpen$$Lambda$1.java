package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.WebBrowserActivity.TikiOpen;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$TikiOpen$$Lambda$1 implements Runnable {
    private final TikiOpen f1595a;
    private final String f1596b;

    private WebBrowserActivity$TikiOpen$$Lambda$1(TikiOpen tikiOpen, String str) {
        this.a = tikiOpen;
        this.b = str;
    }

    public static Runnable lambdaFactory$(TikiOpen tikiOpen, String str) {
        return new WebBrowserActivity$TikiOpen$$Lambda$1(tikiOpen, str);
    }

    @Hidden
    public void run() {
        this.a.lambda$showAndroidPay$0(this.b);
    }
}
