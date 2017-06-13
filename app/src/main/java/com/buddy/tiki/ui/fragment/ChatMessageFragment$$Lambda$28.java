package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$28 implements Consumer {
    private final ChatMessageFragment f2062a;
    private final boolean f2063b;
    private final long f2064c;

    private ChatMessageFragment$$Lambda$28(ChatMessageFragment chatMessageFragment, boolean z, long j) {
        this.a = chatMessageFragment;
        this.b = z;
        this.c = j;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment, boolean z, long j) {
        return new ChatMessageFragment$$Lambda$28(chatMessageFragment, z, j);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1272a(this.b, this.c, (Throwable) obj);
    }
}
