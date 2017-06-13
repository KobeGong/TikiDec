package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.payment.SendGiftResult;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$27 implements Consumer {
    private final ChatMessageFragment f2058a;
    private final boolean f2059b;
    private final long f2060c;
    private final String f2061d;

    private ChatMessageFragment$$Lambda$27(ChatMessageFragment chatMessageFragment, boolean z, long j, String str) {
        this.a = chatMessageFragment;
        this.b = z;
        this.c = j;
        this.d = str;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment, boolean z, long j, String str) {
        return new ChatMessageFragment$$Lambda$27(chatMessageFragment, z, j, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1271a(this.b, this.c, this.d, (SendGiftResult) obj);
    }
}
