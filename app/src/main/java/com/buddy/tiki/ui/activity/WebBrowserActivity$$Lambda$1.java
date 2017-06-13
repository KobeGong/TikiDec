package com.buddy.tiki.ui.activity;

import com.buddy.tiki.service.payment.IabHelper.OnIabSetupFinishedListener;
import com.buddy.tiki.service.payment.IabResult;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebBrowserActivity$$Lambda$1 implements OnIabSetupFinishedListener {
    private final WebBrowserActivity f1585a;

    private WebBrowserActivity$$Lambda$1(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public static OnIabSetupFinishedListener lambdaFactory$(WebBrowserActivity webBrowserActivity) {
        return new WebBrowserActivity$$Lambda$1(webBrowserActivity);
    }

    @Hidden
    public void onIabSetupFinished(IabResult iabResult) {
        this.a.m959a(iabResult);
    }
}
