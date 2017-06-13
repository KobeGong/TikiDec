package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class ChatMessageFragment$$Lambda$24 implements Consumer {
    private final ChatMessageFragment f2055a;

    private ChatMessageFragment$$Lambda$24(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$24(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1270a((List) obj);
    }
}
