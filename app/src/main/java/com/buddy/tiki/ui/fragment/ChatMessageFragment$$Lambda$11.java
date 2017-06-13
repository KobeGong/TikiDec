package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$11 implements Consumer {
    private final ChatMessageFragment f2041a;

    private ChatMessageFragment$$Lambda$11(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$11(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1285d(obj);
    }
}
