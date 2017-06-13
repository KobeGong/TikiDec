package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.ui.dialog.GiftDialog.PresentListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$65 implements PresentListener {
    private final CallActivity f1207a;

    private CallActivity$$Lambda$65(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static PresentListener lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$65(callActivity);
    }

    @Hidden
    public void onPresent(Gift gift) {
        this.a.m521b(gift);
    }
}
