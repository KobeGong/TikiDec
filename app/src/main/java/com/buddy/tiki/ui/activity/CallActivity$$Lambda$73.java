package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$73 implements OnClickListener {
    private final CallActivity f1218a;

    private CallActivity$$Lambda$73(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static OnClickListener lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$73(callActivity);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.m614b(dialogInterface, i);
    }
}
