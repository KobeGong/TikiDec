package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$36 implements Consumer {
    private final ChatMessageFragment f2078a;

    private ChatMessageFragment$$Lambda$36(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$36(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1266a((OperInfo) obj);
    }
}
