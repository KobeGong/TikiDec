package com.buddy.tiki.ui.fragment;

import android.view.MotionEvent;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$13 implements Consumer {
    private final ChatMessageFragment f2043a;

    private ChatMessageFragment$$Lambda$13(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static Consumer lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$13(chatMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1276b((MotionEvent) obj);
    }
}
