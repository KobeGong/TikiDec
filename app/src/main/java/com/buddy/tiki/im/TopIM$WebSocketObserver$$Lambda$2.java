package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;
import org.json.JSONObject;

final /* synthetic */ class TopIM$WebSocketObserver$$Lambda$2 implements Runnable {
    private final WebSocketObserver f867a;
    private final JSONObject f868b;

    private TopIM$WebSocketObserver$$Lambda$2(WebSocketObserver webSocketObserver, JSONObject jSONObject) {
        this.a = webSocketObserver;
        this.b = jSONObject;
    }

    public static Runnable lambdaFactory$(WebSocketObserver webSocketObserver, JSONObject jSONObject) {
        return new TopIM$WebSocketObserver$$Lambda$2(webSocketObserver, jSONObject);
    }

    @Hidden
    public void run() {
        this.a.m248a(this.b);
    }
}
