package com.buddy.tiki.im;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class NetworkStateReceiver$$Lambda$1 implements Predicate {
    private static final NetworkStateReceiver$$Lambda$1 f857a = new NetworkStateReceiver$$Lambda$1();

    private NetworkStateReceiver$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return NetworkStateReceiver.m244c((ConfigInfo) obj);
    }
}
