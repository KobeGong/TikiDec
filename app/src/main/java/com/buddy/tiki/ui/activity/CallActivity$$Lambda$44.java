package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$44 implements Predicate {
    private static final CallActivity$$Lambda$44 f1180a = new CallActivity$$Lambda$44();

    private CallActivity$$Lambda$44() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return CallActivity.m533c((OperInfo) obj);
    }
}
