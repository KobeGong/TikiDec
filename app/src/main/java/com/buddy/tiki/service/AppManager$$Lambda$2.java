package com.buddy.tiki.service;

import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AppManager$$Lambda$2 implements Function {
    private static final AppManager$$Lambda$2 f900a = new AppManager$$Lambda$2();

    private AppManager$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return PreferenceUtil.getConfigInfoCache();
    }
}
