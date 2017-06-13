package com.buddy.tiki.ui.activity;

import com.buddy.tiki.view.BottomNavigationView.OnNavigationViewClick;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$16 implements OnNavigationViewClick {
    private final CallActivity f1143a;

    private CallActivity$$Lambda$16(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static OnNavigationViewClick lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$16(callActivity);
    }

    @Hidden
    public void onItemClick(int i) {
        this.a.m621c(i);
    }
}
