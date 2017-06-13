package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$36 implements OnCancelListener {
    private final CallFriendActivity f1330a;

    private CallFriendActivity$$Lambda$36(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static OnCancelListener lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$36(callFriendActivity);
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        this.a.m693a(dialogInterface);
    }
}
