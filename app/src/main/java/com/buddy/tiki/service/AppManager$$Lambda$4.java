package com.buddy.tiki.service;

import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AppManager$$Lambda$4 implements Function {
    private static final AppManager$$Lambda$4 f902a = new AppManager$$Lambda$4();

    private AppManager$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return PreferenceUtil.getOperInfoCache();
    }
}
