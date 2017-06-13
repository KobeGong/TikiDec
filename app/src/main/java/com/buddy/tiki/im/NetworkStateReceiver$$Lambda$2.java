package com.buddy.tiki.im;

import com.buddy.tiki.base.TopConfig;
import com.buddy.tiki.model.app.ConfigInfo;
import com.buddy.tiki.model.constant.DomainType;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class NetworkStateReceiver$$Lambda$2 implements Consumer {
    private static final NetworkStateReceiver$$Lambda$2 f858a = new NetworkStateReceiver$$Lambda$2();

    private NetworkStateReceiver$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        IMRtcClient.getInstance().connect(((ConfigInfo) obj).getWsuris()[0] + String.format("?u=%s&s=%s&domain=%s&v=4", new Object[]{TopConfig.f408a, TopConfig.f409b, DomainType.BIZ}));
    }
}
