package com.buddy.tiki.ui.activity;

import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SplashActivity$$Lambda$4 implements Action {
    private static final SplashActivity$$Lambda$4 f1464a = new SplashActivity$$Lambda$4();

    private SplashActivity$$Lambda$4() {
    }

    public static Action lambdaFactory$() {
        return a;
    }

    @Hidden
    public void run() {
        PreferenceUtil.setActiveFlag();
    }
}
