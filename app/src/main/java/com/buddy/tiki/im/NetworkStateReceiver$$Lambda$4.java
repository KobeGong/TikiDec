package com.buddy.tiki.im;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class NetworkStateReceiver$$Lambda$4 implements Consumer {
    private static final NetworkStateReceiver$$Lambda$4 f860a = new NetworkStateReceiver$$Lambda$4();

    private NetworkStateReceiver$$Lambda$4() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        NetworkStateReceiver.m242a((Throwable) obj);
    }
}
