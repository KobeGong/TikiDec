package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$19 implements Consumer {
    private static final ChatMessageFragment$$Lambda$19 f2049a = new ChatMessageFragment$$Lambda$19();

    private ChatMessageFragment$$Lambda$19() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        ChatMessageFragment.f2093c.m263e("throwable=" + ((Throwable) obj));
    }
}
