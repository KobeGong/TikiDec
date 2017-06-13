package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class WebSocketConnectionHint$$Lambda$1 implements Runnable {
    private final WebSocketConnectionHint f2919a;

    private WebSocketConnectionHint$$Lambda$1(WebSocketConnectionHint webSocketConnectionHint) {
        this.a = webSocketConnectionHint;
    }

    public static Runnable lambdaFactory$(WebSocketConnectionHint webSocketConnectionHint) {
        return new WebSocketConnectionHint$$Lambda$1(webSocketConnectionHint);
    }

    @Hidden
    public void run() {
        this.a.m1815a();
    }
}
