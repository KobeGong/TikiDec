package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$8 implements Consumer {
    private final ChatMessageFragment f2086a;

    private ChatMessageFragment$$Lambda$8(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$8(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1291g(obj);
    }
}
