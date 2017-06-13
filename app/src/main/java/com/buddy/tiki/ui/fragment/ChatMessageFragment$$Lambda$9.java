package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$9 implements Consumer {
    private final ChatMessageFragment f2087a;

    private ChatMessageFragment$$Lambda$9(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$9(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1290f(obj);
    }
}
