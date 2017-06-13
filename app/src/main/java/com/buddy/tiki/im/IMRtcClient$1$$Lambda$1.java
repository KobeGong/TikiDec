package com.buddy.tiki.im;

import com.buddy.tiki.im.IMRtcClient.C03971;
import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$1$$Lambda$1 implements Predicate {
    private static final IMRtcClient$1$$Lambda$1 f828a = new IMRtcClient$1$$Lambda$1();

    private IMRtcClient$1$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return C03971.m214c((ConfigInfo) obj);
    }
}
