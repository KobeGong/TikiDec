package com.buddy.tiki.ui.fragment;

import io.realm.RealmChangeListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$2 implements RealmChangeListener {
    private final ChatMessageFragment f2069a;

    private ChatMessageFragment$$Lambda$2(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static RealmChangeListener lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$2(chatMessageFragment);
    }

    @Hidden
    public void onChange(Object obj) {
        this.a.m1294j(obj);
    }
}
