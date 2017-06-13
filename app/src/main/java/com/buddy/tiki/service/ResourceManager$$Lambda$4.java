package com.buddy.tiki.service;

import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ResourceManager$$Lambda$4 implements Function {
    private static final ResourceManager$$Lambda$4 f938a = new ResourceManager$$Lambda$4();

    private ResourceManager$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return PreferenceUtil.getFaceuAvatarCache();
    }
}
