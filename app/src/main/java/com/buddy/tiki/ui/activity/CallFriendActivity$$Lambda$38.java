package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$38 implements OnDismissListener {
    private final CallFriendActivity f1332a;

    private CallFriendActivity$$Lambda$38(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static OnDismissListener lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$38(callFriendActivity);
    }

    @Hidden
    public void onDismiss(DialogInterface dialogInterface) {
        this.a.m702b(dialogInterface);
    }
}
