package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$29 implements Action {
    private final ChatMessageFragment f2065a;
    private final boolean f2066b;
    private final String f2067c;
    private final String f2068d;

    private ChatMessageFragment$$Lambda$29(ChatMessageFragment chatMessageFragment, boolean z, String str, String str2) {
        this.a = chatMessageFragment;
        this.b = z;
        this.c = str;
        this.d = str2;
    }

    public static Action lambdaFactory$(ChatMessageFragment chatMessageFragment, boolean z, String str, String str2) {
        return new ChatMessageFragment$$Lambda$29(chatMessageFragment, z, str, str2);
    }

    @Hidden
    public void run() {
        this.a.m1273a(this.b, this.c, this.d);
    }
}
