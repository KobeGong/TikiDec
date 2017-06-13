package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$37 implements Consumer {
    private final ChatMessageFragment f2079a;

    private ChatMessageFragment$$Lambda$37(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$37(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1288e((Throwable) obj);
    }
}
