package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TopIM$WebSocketObserver$$Lambda$1 implements Runnable {
    private final WebSocketObserver f866a;

    private TopIM$WebSocketObserver$$Lambda$1(WebSocketObserver webSocketObserver) {
        this.a = webSocketObserver;
    }

    public static Runnable lambdaFactory$(WebSocketObserver webSocketObserver) {
        return new TopIM$WebSocketObserver$$Lambda$1(webSocketObserver);
    }

    @Hidden
    public void run() {
        this.a.m247a();
    }
}
