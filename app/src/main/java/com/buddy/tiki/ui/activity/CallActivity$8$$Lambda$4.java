package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.CallActivity.C04258;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$8$$Lambda$4 implements Function {
    private final C04258 f1240a;
    private final String f1241b;

    private CallActivity$8$$Lambda$4(C04258 c04258, String str) {
        this.a = c04258;
        this.b = str;
    }

    public static Function lambdaFactory$(C04258 c04258, String str) {
        return new CallActivity$8$$Lambda$4(c04258, str);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m472a(this.b, (String) obj);
    }
}
