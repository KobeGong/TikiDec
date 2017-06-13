package com.buddy.tiki.ui.fragment;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$35 implements Runnable {
    private final ChatMessageFragment f2077a;

    private ChatMessageFragment$$Lambda$35(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Runnable lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$35(chatMessageFragment);
    }

    @Hidden
    public void run() {
        this.a.m1280c();
    }
}
