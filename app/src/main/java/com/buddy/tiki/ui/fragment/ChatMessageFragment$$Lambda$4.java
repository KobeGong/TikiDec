package com.buddy.tiki.ui.fragment;

import io.realm.RealmChangeListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$4 implements RealmChangeListener {
    private final ChatMessageFragment f2082a;

    private ChatMessageFragment$$Lambda$4(ChatMessageFragment chatMessageFragment) {
        this.a = chatMessageFragment;
    }

    public static RealmChangeListener lambdaFactory$(ChatMessageFragment chatMessageFragment) {
        return new ChatMessageFragment$$Lambda$4(chatMessageFragment);
    }

    @Hidden
    public void onChange(Object obj) {
        this.a.m1293i(obj);
    }
}
