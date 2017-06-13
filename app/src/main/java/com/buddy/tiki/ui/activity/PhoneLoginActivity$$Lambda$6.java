package com.buddy.tiki.ui.activity;

import io.reactivex.functions.BiFunction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PhoneLoginActivity$$Lambda$6 implements BiFunction {
    private static final PhoneLoginActivity$$Lambda$6 f1448a = new PhoneLoginActivity$$Lambda$6();

    private PhoneLoginActivity$$Lambda$6() {
    }

    public static BiFunction lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj, Object obj2) {
        return Boolean.valueOf(((Boolean) obj).booleanValue() & ((Boolean) obj2).booleanValue());
    }
}
