package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$20 implements Consumer {
    private final ChatMessageFragment f2051a;

    private ChatMessageFragment$$Lambda$20(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$20(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1265a((ConfigInfo) obj);
    }
}
