package com.buddy.tiki.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$71 implements OnClickListener {
    private final CallActivity f1216a;

    private CallActivity$$Lambda$71(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static OnClickListener lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$71(callActivity);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.m623c(dialogInterface, i);
    }
}
