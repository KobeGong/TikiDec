package com.buddy.tiki.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$22 implements OnClickListener {
    private final CallActivity f1150a;

    private CallActivity$$Lambda$22(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static OnClickListener lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$22(callActivity);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m615b(view);
    }
}
