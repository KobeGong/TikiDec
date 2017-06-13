package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$25 implements Consumer {
    private static final ChatMessageFragment$$Lambda$25 f2056a = new ChatMessageFragment$$Lambda$25();

    private ChatMessageFragment$$Lambda$25() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        ChatMessageFragment.f2093c.m264e("fetchList: ", (Throwable) obj);
    }
}
