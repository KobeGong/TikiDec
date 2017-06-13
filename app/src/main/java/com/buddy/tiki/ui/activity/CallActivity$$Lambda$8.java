package com.buddy.tiki.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.app.VersionInfo;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$8 implements OnClickListener {
    private final CallActivity f1221a;
    private final VersionInfo f1222b;

    private CallActivity$$Lambda$8(CallActivity callActivity, VersionInfo versionInfo) {
        this.a = callActivity;
        this.b = versionInfo;
    }

    public static OnClickListener lambdaFactory$(CallActivity callActivity, VersionInfo versionInfo) {
        return new CallActivity$$Lambda$8(callActivity, versionInfo);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m601a(this.b, view);
    }
}
