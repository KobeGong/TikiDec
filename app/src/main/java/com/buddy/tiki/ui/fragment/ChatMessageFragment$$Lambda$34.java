package com.buddy.tiki.ui.fragment;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$34 implements Runnable {
    private final ChatMessageFragment f2076a;

    private ChatMessageFragment$$Lambda$34(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Runnable lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$34(chatMessageFragment);
    }

    @Hidden
    public void run() {
        this.a.m1275b();
    }
}
