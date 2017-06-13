package com.buddy.tiki.im;

import com.buddy.tiki.im.IMRtcClient.C03971;
import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$1$$Lambda$3 implements Consumer {
    private static final IMRtcClient$1$$Lambda$3 f830a = new IMRtcClient$1$$Lambda$3();

    private IMRtcClient$1$$Lambda$3() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        C03971.m211a((ConfigInfo) obj);
    }
}
