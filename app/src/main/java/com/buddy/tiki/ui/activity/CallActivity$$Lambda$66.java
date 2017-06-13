package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$66 implements OnDismissListener {
    private final CallActivity f1208a;

    private CallActivity$$Lambda$66(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static OnDismissListener lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$66(callActivity);
    }

    @Hidden
    public void onDismiss(DialogInterface dialogInterface) {
        this.a.m613b(dialogInterface);
    }
}
