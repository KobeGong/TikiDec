package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$64 implements OnCancelListener {
    private final CallActivity f1206a;

    private CallActivity$$Lambda$64(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static OnCancelListener lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$64(callActivity);
    }

    @Hidden
    public void onCancel(DialogInterface dialogInterface) {
        this.a.m596a(dialogInterface);
    }
}
