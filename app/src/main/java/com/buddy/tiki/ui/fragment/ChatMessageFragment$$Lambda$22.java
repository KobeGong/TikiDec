package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$22 implements Consumer {
    private final ChatMessageFragment f2053a;

    private ChatMessageFragment$$Lambda$22(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$22(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1267a((User) obj);
    }
}
