package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.resource.Gift;
import com.buddy.tiki.ui.dialog.GiftDialog.PresentListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$37 implements PresentListener {
    private final CallFriendActivity f1331a;

    private CallFriendActivity$$Lambda$37(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static PresentListener lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$37(callFriendActivity);
    }

    @Hidden
    public void onPresent(Gift gift) {
        this.a.m651b(gift);
    }
}
