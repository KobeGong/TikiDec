package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.WebBrowserActivity.TikiOpen;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$TikiOpen$$Lambda$3 implements Runnable {
    private final TikiOpen f1599a;
    private final String f1600b;

    private WebBrowserActivity$TikiOpen$$Lambda$3(TikiOpen tikiOpen, String str) {
        this.a = tikiOpen;
        this.b = str;
    }

    public static Runnable lambdaFactory$(TikiOpen tikiOpen, String str) {
        return new WebBrowserActivity$TikiOpen$$Lambda$3(tikiOpen, str);
    }

    @Hidden
    public void run() {
        this.a.lambda$setRightButtonText$2(this.b);
    }
}
