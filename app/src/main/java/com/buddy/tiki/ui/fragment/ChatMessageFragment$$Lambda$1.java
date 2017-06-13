package com.buddy.tiki.ui.fragment;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$1 implements Runnable {
    private final ChatMessageFragment f2050a;

    private ChatMessageFragment$$Lambda$1(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Runnable lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$1(chatMessageFragment);
    }

    @Hidden
    public void run() {
        this.a.m1289f();
    }
}
