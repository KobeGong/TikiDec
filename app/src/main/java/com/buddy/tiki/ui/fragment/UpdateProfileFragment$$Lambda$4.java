package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.BiFunction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$4 implements BiFunction {
    private static final UpdateProfileFragment$$Lambda$4 f2242a = new UpdateProfileFragment$$Lambda$4();

    private UpdateProfileFragment$$Lambda$4() {
    }

    public static BiFunction lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj, Object obj2) {
        return Boolean.valueOf(((Boolean) obj).booleanValue() & ((Boolean) obj2).booleanValue());
    }
}
