package com.buddy.tiki.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.ui.activity.CallActivity.C04247;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$7$$Lambda$1 implements OnClickListener {
    private final C04247 f1234a;

    private CallActivity$7$$Lambda$1(C04247 c04247) {
        this.a = c04247;
    }

    public static OnClickListener lambdaFactory$(C04247 c04247) {
        return new CallActivity$7$$Lambda$1(c04247);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m468a(view);
    }
}
