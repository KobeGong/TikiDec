package com.buddy.tiki.ui.adapter;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageAdapter$$Lambda$9 implements Consumer {
    private final ChatMessageAdapter f1696a;

    private ChatMessageAdapter$$Lambda$9(ChatMessageAdapter chatMessageAdapter) {
        this.a = chatMessageAdapter;
    }

    public static Consumer lambdaFactory$(ChatMessageAdapter chatMessageAdapter) {
        return new ChatMessageAdapter$$Lambda$9(chatMessageAdapter);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1007a((Throwable) obj);
    }
}
