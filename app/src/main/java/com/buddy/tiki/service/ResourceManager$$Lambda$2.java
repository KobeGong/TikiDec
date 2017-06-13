package com.buddy.tiki.service;

import com.buddy.tiki.util.PreferenceUtil;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ResourceManager$$Lambda$2 implements Function {
    private static final ResourceManager$$Lambda$2 f935a = new ResourceManager$$Lambda$2();

    private ResourceManager$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return PreferenceUtil.getGiftListCache();
    }
}
