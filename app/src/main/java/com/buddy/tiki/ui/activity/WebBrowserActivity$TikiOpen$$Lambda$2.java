package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.WebBrowserActivity.TikiOpen;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$TikiOpen$$Lambda$2 implements Runnable {
    private final TikiOpen f1597a;
    private final boolean f1598b;

    private WebBrowserActivity$TikiOpen$$Lambda$2(TikiOpen tikiOpen, boolean z) {
        this.a = tikiOpen;
        this.b = z;
    }

    public static Runnable lambdaFactory$(TikiOpen tikiOpen, boolean z) {
        return new WebBrowserActivity$TikiOpen$$Lambda$2(tikiOpen, z);
    }

    @Hidden
    public void run() {
        this.a.lambda$setRightButtonHidden$1(this.b);
    }
}
