package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.WebBrowserActivity.TikiOpen;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$TikiOpen$$Lambda$4 implements Consumer {
    private final TikiOpen f1601a;
    private final String f1602b;

    private WebBrowserActivity$TikiOpen$$Lambda$4(TikiOpen tikiOpen, String str) {
        this.a = tikiOpen;
        this.b = str;
    }

    public static Consumer lambdaFactory$(TikiOpen tikiOpen, String str) {
        return new WebBrowserActivity$TikiOpen$$Lambda$4(tikiOpen, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.lambda$oauth$3(this.b, (String) obj);
    }
}
