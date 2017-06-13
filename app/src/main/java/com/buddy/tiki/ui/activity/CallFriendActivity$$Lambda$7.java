package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$7 implements OnClickListener {
    private final CallFriendActivity f1338a;

    private CallFriendActivity$$Lambda$7(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static OnClickListener lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$7(callFriendActivity);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.m704c(dialogInterface, i);
    }
}
