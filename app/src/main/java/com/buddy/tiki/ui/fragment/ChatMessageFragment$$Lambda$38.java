package com.buddy.tiki.ui.fragment;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$38 implements Runnable {
    private final ChatMessageFragment f2080a;

    private ChatMessageFragment$$Lambda$38(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Runnable lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$38(chatMessageFragment);
    }

    @Hidden
    public void run() {
        this.a.m1284d();
    }
}
