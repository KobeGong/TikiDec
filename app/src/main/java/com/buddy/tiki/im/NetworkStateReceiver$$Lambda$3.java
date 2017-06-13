package com.buddy.tiki.im;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class NetworkStateReceiver$$Lambda$3 implements Consumer {
    private static final NetworkStateReceiver$$Lambda$3 f859a = new NetworkStateReceiver$$Lambda$3();

    private NetworkStateReceiver$$Lambda$3() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        NetworkStateReceiver.m241a((ConfigInfo) obj);
    }
}
