package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$30 implements Consumer {
    private final ChatMessageFragment f2070a;
    private final boolean f2071b;
    private final String f2072c;

    private ChatMessageFragment$$Lambda$30(ChatMessageFragment chatMessageFragment, boolean z, String str) {
        this.a = chatMessageFragment;
        this.b = z;
        this.c = str;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment, boolean z, String str) {
        return new ChatMessageFragment$$Lambda$30(chatMessageFragment, z, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1274a(this.b, this.c, (Throwable) obj);
    }
}
